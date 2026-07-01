package com.vivek.stocksentry.service;

import com.vivek.stocksentry.model.StockPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class FinnhubService {

    @Value("${finnhub.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate =
            new RestTemplate();

    private static final String BASE_URL =
            "https://finnhub.io/api/v1/quote?symbol={symbol}&token={apikey}";

    public StockPrice getStockPrice(String symbol) {

        try {

            Map<?, ?> response =
                    restTemplate.getForObject(
                            BASE_URL,
                            Map.class,
                            symbol,
                            apiKey);

            if (response == null ||
                    response.get("c") == null) {
                return null;
            }

            double current =
                    ((Number) response.get("c"))
                            .doubleValue();

            double change =
                    ((Number) response.get("d"))
                            .doubleValue();

            double changePercent =
                    ((Number) response.get("dp"))
                            .doubleValue();

            if (current == 0.0) {
                return null;
            }

            StockPrice stockPrice =
                    new StockPrice();

            stockPrice.setSymbol(symbol);
            stockPrice.setPrice(
                    BigDecimal.valueOf(current));

            stockPrice.setChange(
                    BigDecimal.valueOf(change));

            stockPrice.setChangePercent(
                    BigDecimal.valueOf(changePercent));

            stockPrice.setFetchedAt(
                    LocalDateTime.now());

            return stockPrice;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}