package com.mikayelovich.premium_indicators.core.trade_unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mikayelovich.premium_indicators.core.utils.JsonUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper

public interface TradeUnitMapper {
    TradeUnitMapper INSTANCE = Mappers.getMapper(TradeUnitMapper.class);


    @Mapping(target = "eventType", source = "eventType")
    @Mapping(target = "eventTime", source = "eventTime")
    @Mapping(target = "symbol", source = "symbol")
    @Mapping(target = "tradeId", source = "tradeId")
    @Mapping(target = "price", expression = "java(convertStringToBigDecimal(dto.getPrice()))")
    @Mapping(target = "quantity", expression = "java(convertStringToBigDecimal(dto.getQuantity()))")
    @Mapping(target = "buyerOrderId", source = "buyerOrderId")
    @Mapping(target = "sellerOrderId", source = "sellerOrderId")
    @Mapping(target = "tradeTime", source = "tradeTime")
    @Mapping(target = "isBuyerMarketMaker", source = "buyerMarketMaker")
    TradeUnit dtoToEntity(TradeUnitDTO dto);

    @Mapping(target = "eventType", source = "eventType")
    @Mapping(target = "eventTime", source = "eventTime")
    @Mapping(target = "symbol", source = "symbol")
    @Mapping(target = "tradeId", source = "tradeId")
    @Mapping(target = "price", expression = "java(convertBigDecimalToString(entity.getPrice()))")
    @Mapping(target = "quantity", expression = "java(convertBigDecimalToString(entity.getQuantity()))")
    @Mapping(target = "buyerOrderId", source = "buyerOrderId")
    @Mapping(target = "sellerOrderId", source = "sellerOrderId")
    @Mapping(target = "tradeTime", source = "tradeTime")
    @Mapping(target = "buyerMarketMaker", source = "isBuyerMarketMaker")
    TradeUnitDTO entityToDto(TradeUnit entity);

    // Helper methods for type conversion
    default BigDecimal convertStringToBigDecimal(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            // Handle the case where the string can't be converted to BigDecimal
            // This might depend on your application's requirements
            throw new IllegalArgumentException("Invalid format for BigDecimal: " + value, e);
        }
    }

    default String convertBigDecimalToString(BigDecimal value) {
        return value != null ? value.toString() : null;
    }

    default TradeUnitDTO jsonTODto(String data) throws JsonProcessingException {
        return JsonUtils.jsonToObject(data, TradeUnitDTO.class);
    }
}