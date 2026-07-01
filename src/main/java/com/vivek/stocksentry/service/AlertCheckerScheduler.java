package com.vivek.stocksentry.service;

import com.vivek.stocksentry.entity.PriceAlert;
import com.vivek.stocksentry.model.StockPrice;
import com.vivek.stocksentry.repository.PriceAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AlertCheckerScheduler {

    @Autowired
    private PriceAlertRepository priceAlertRepository;

    @Autowired
    private FinnhubService finnhubService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private WhatsAppService whatsAppService;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void checkAlerts() {
        List<PriceAlert> activeAlerts = priceAlertRepository.findByIsActiveTrue();

        System.out.println("Checking " + activeAlerts.size() + " active alerts...");

        for (PriceAlert alert : activeAlerts) {
            StockPrice stockPrice =
                    finnhubService.getStockPrice(
                            alert.getSymbol());

            if (stockPrice == null) {
                continue;
            }

            boolean triggered = false;

            if (alert.getConditionType() == PriceAlert.ConditionType.ABOVE
                    && stockPrice.getPrice().compareTo(alert.getTargetPrice()) >= 0) {
                triggered = true;
            } else if (alert.getConditionType() == PriceAlert.ConditionType.BELOW
                    && stockPrice.getPrice().compareTo(alert.getTargetPrice()) <= 0) {
                triggered = true;
            }

            if (triggered) {
                sendNotifications(alert, stockPrice);
                alert.setLastTriggered(LocalDateTime.now());
                alert.setIsActive(false);
                priceAlertRepository.save(alert);
            }
        }
    }

    private void sendNotifications(PriceAlert alert, StockPrice stockPrice) {
        String message = String.format(
                "StockSentry Alert: %s has crossed your target price of $%s. Current price: $%s",
                alert.getSymbol(), alert.getTargetPrice(), stockPrice.getPrice()
        );

        String userEmail = alert.getUser().getEmail();
        String userPhone = alert.getUser().getPhoneNumber();

        try {
            emailService.sendEmail(userEmail, "StockSentry Price Alert: " + alert.getSymbol(), message);
            System.out.println("Email sent to " + userEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }

        if (userPhone != null && !userPhone.isEmpty()) {
            try {
                whatsAppService.sendWhatsAppMessage(userPhone, message);
                System.out.println("WhatsApp sent to " + userPhone);
            } catch (Exception e) {
                System.err.println("Failed to send WhatsApp: " + e.getMessage());
            }
        }
    }
}