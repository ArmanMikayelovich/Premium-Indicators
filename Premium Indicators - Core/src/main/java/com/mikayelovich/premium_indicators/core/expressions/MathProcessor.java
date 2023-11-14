package com.mikayelovich.premium_indicators.core.expressions;

import com.mikayelovich.premium_indicators.core.trade_unit.ExpressionArgument;
import org.apache.commons.beanutils.PropertyUtils;
import org.mariuszgromada.math.mxparser.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MathProcessor {

    private static final Logger log = LoggerFactory.getLogger(MathProcessor.class);

    public static void process(CalculationExpression calculationExpressionModel) {
        String mathExpression = calculationExpressionModel.getFormula().getMathExpression();
        Map<String, List<? extends ExpressionArgument>> parameters = calculationExpressionModel.getParameters();
        List<String> list = parameters.keySet().stream().filter(key -> mathExpression.contains(key + ".")).toList();
        for (String listName : list) {
            String patternString = listName + "\\.([a-zA-Z0-9]+)";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(mathExpression);
            if (matcher.find()) {
                String fieldName = matcher.group(1);
                System.out.println(fieldName);
                String replacer = extractValues(calculationExpressionModel.getParameters().get(listName), fieldName).stream()
                        .map(String::valueOf).collect(Collectors.joining(", "));
                var newMathExpression = mathExpression.replace(listName + "." + fieldName, replacer);
                log.error(newMathExpression);
                Expression e1 = new Expression(newMathExpression);

                double calculate = e1.calculate();
                log.error("RESULT:   {}", calculate);
                log.error("RESULT:   {}", calculate);
                log.error("RESULT:   {}", calculate);
                log.error("RESULT:   {}", calculate);

            }
        }
    }


    static <T> List<T> extractValues(List<? extends ExpressionArgument> objectList, String fieldName) {
        List<T> valueList = new ArrayList<>();

        for (ExpressionArgument serializable : objectList) {
            try {
                T value = (T) PropertyUtils.getSimpleProperty(serializable, fieldName);
                valueList.add(value);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error(String.valueOf(e));
                throw new RuntimeException(e);
            }
        }
        return valueList;
    }

}
