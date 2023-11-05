package com.mikayelovich.premium_indicators.core.expressions;

import com.mikayelovich.premium_indicators.core.trade_unit.ExpressionArgument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CalculationExpression {
   private  Formula formula;
    private  Map<String, List<? extends ExpressionArgument>> parameters;

}
