package com.vivek.stocksentry.repository;

import com.vivek.stocksentry.entity.PriceAlert;
import com.vivek.stocksentry.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface PriceAlertRepository extends JpaRepository<PriceAlert, Long> {

    List<PriceAlert> findByUserId(Long userId);

    List<PriceAlert> findByIsActiveTrue();

    List<PriceAlert> findByUserIdAndSymbol(Long userId, String symbol);

    boolean existsByUserAndSymbolAndConditionTypeAndTargetPrice(
            User user,
            String symbol,
            PriceAlert.ConditionType conditionType,
            BigDecimal targetPrice
    );
}