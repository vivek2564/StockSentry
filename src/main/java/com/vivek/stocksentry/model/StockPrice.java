package com.vivek.stocksentry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StockPrice {
    private String symbol;
    private BigDecimal price;
    private BigDecimal change;
    private BigDecimal changePercent;
    private LocalDateTime fetchedAt;
}