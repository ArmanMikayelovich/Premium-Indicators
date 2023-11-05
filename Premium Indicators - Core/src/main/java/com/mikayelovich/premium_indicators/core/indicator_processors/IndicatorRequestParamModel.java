package com.mikayelovich.premium_indicators.core.indicator_processors;

import lombok.Data;

@Data
class IndicatorRequestParamModel {
    String name;
    String fullClassName;

    String expressionForFetchingFromDB;
}


