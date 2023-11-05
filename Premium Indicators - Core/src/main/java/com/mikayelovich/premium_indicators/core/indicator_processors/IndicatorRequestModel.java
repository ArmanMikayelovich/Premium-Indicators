package com.mikayelovich.premium_indicators.core.indicator_processors;

import lombok.Data;

import java.util.List;

@Data
class IndicatorRequestModel {
    private String expression;

    private List<IndicatorRequestParamModel> params;

}