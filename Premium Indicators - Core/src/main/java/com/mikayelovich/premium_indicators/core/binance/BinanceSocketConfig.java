package com.mikayelovich.premium_indicators.core.binance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikayelovich.premium_indicators.core.trade_unit.TradeUnitDTO;
import com.mikayelovich.premium_indicators.core.trade_unit.TradeUnitMapper;
import com.mikayelovich.premium_indicators.core.trade_unit.TradeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.util.function.Consumer;

@Configuration
public class BinanceSocketConfig {
    private static final String str = "wss://stream.binance.com:9443/ws/btcusdt@trade";

    private final TradeUnitService tradeUnitService;
    private final TradeUnitMapper tradeUnitMapper = TradeUnitMapper.INSTANCE;


    @Autowired
    public BinanceSocketConfig(TradeUnitService tradeUnitService) {
        this.tradeUnitService = tradeUnitService;
    }

    @Bean
    public BinanceWebSocketClient binanceWebSocketClient() {
        Consumer<String> saveToDB = (data) -> {
            try {
                TradeUnitDTO tradeUnitDTO = tradeUnitMapper.jsonTODto(data);
                tradeUnitService.saveTradeUnit(tradeUnitDTO);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        };
        BinanceWebSocketClient client = new BinanceWebSocketClient(URI.create(str), saveToDB);
        client.connect();
        return client;
    }
}
