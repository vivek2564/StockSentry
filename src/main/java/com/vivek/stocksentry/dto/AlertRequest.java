package com.vivek.stocksentry.dto;

import com.vivek.stocksentry.entity.PriceAlert.ConditionType;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class AlertRequest {
    private String symbol;
    private BigDecimal targetPrice;
    private ConditionType conditionType;
}