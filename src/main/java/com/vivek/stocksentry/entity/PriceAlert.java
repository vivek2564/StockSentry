package com.vivek.stocksentry.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "price_alerts",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "user_id",
                                "symbol",
                                "condition_type",
                                "target_price"
                        }
                )
        }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PriceAlert {
    public enum ConditionType { ABOVE, BELOW }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 10)
    private String symbol;

    @Column(name = "target_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal targetPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_type", nullable = false)
    private ConditionType conditionType;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "last_triggered")
    private LocalDateTime lastTriggered;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }
}