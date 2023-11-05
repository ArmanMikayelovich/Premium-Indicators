package com.mikayelovich.premium_indicators.core.indicator_processors;

import java.util.List;

public interface IndicatorProcessor<UNIT_TYPE,RESUT_TYPE> {

    RESUT_TYPE process(List<UNIT_TYPE> entities);

}
