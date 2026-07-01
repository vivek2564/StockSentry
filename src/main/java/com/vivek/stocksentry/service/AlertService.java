package com.vivek.stocksentry.service;

import com.vivek.stocksentry.entity.PriceAlert;
import com.vivek.stocksentry.entity.PriceAlert.ConditionType;
import com.vivek.stocksentry.entity.User;
import com.vivek.stocksentry.repository.PriceAlertRepository;
import com.vivek.stocksentry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private PriceAlertRepository priceAlertRepository;

    @Autowired
    private UserRepository userRepository;

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public PriceAlert createAlert(String username,
                                  String symbol,
                                  BigDecimal targetPrice,
                                  ConditionType conditionType) {

        User user = getUser(username);

        symbol = symbol.toUpperCase();

        boolean exists =
                priceAlertRepository
                        .existsByUserAndSymbolAndConditionTypeAndTargetPrice(
                                user,
                                symbol,
                                conditionType,
                                targetPrice
                        );

        if (exists) {
            throw new RuntimeException("Alert already exists");
        }

        PriceAlert alert = PriceAlert.builder()
                .user(user)
                .symbol(symbol)
                .targetPrice(targetPrice)
                .conditionType(conditionType)
                .build();

        return priceAlertRepository.save(alert);
    }

    public List<PriceAlert> getAlerts(String username) {
        User user = getUser(username);
        return priceAlertRepository.findByUserId(user.getId());
    }

    public void deleteAlert(String username, Long alertId) {
        PriceAlert alert = priceAlertRepository.findById(alertId)
                .orElseThrow(() -> new RuntimeException("Alert not found"));

        if (!alert.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }

        priceAlertRepository.delete(alert);
    }
}