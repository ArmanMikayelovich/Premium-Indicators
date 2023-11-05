package com.mikayelovich.premium_indicators.core.indicator_processors;

import com.mikayelovich.premium_indicators.core.trade_unit.TradeUnitDTO;
import org.mariuszgromada.math.mxparser.*;
import java.util.List;

public class TradeUnitMaxPriceForPeriodIndicatorProcessor implements IndicatorProcessor<TradeUnitDTO, Integer> {

    @Override
    public Integer process(List<TradeUnitDTO> entities) {

        Expression e1 = new Expression("2%");
        Expression e2 = new Expression("2% * 100");
        Expression e3 = new Expression("pi% * 100");
        mXparser.consolePrintln("Res 1: " + e1.getExpressionString() + " = " + e1.calculate());
        mXparser.consolePrintln("Res 2: " + e2.getExpressionString() + " = " + e2.calculate());
        mXparser.consolePrintln("Res 3: " + e3.getExpressionString() + " = " + e3.calculate());
        return 1;
    }
}
