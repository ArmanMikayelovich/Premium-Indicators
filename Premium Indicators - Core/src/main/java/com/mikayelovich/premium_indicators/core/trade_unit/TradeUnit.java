package com.mikayelovich.premium_indicators.core.trade_unit;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "trande_unit")
@Data
public class TradeUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "event_time")
    private Long eventTime;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "trade_id")
    private Long tradeId;

    @Column(name = "price", precision = 19, scale = 4)
    private BigDecimal price;

    @Column(name = "quantity", precision = 19, scale = 8)
    private BigDecimal quantity;

    @Column(name = "buyer_order_id")
    private Long buyerOrderId;

    @Column(name = "seller_order_id")
    private Long sellerOrderId;

    @Column(name = "trade_time")
    private Long tradeTime;

    @Column(name = "is_buyer_market_maker")
    private Boolean isBuyerMarketMaker;

}
