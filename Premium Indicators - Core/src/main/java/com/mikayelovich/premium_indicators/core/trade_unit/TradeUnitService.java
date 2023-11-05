package com.mikayelovich.premium_indicators.core.trade_unit;

import com.mikayelovich.premium_indicators.core.binance.BinanceWebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
 public class TradeUnitService {
    private static final Logger log = LoggerFactory.getLogger(TradeUnitService.class);

    private TradeUnitMapper tradeUnitMapper = TradeUnitMapper.INSTANCE;

    private final TradeUnitRepository tradeUnitRepository;

    @Autowired
    public TradeUnitService(TradeUnitRepository tradeUnitRepository) {
        this.tradeUnitRepository = tradeUnitRepository;
    }

    public void saveTradeUnit(TradeUnitDTO tradeUnitDTO) {


        TradeUnit tradeUnit = tradeUnitMapper.dtoToEntity(tradeUnitDTO);
        tradeUnitRepository.save(tradeUnit);
    }

}
