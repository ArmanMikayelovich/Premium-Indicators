package com.mikayelovich.premium_indicators.core.utils;

import com.mikayelovich.premium_indicators.core.trade_unit.TradeUnitDTO;

import java.lang.reflect.Field;
import java.util.*;

public final class ReflectionUtils {
    private ReflectionUtils(){}

    private static List<Class<?>> CLASSES = List.of(
            TradeUnitDTO.class
    );

    public static Map<String, List<String>> MODELS_WITH_FIELDS = getClassesWithTheirFields();

    private static Map<String, List<String>> getClassesWithTheirFields() {
        Map<String, List<String>>  modelsWithFieldsMap = new HashMap<>();
        for (Class<?> aClass : CLASSES) {
            List<String> fieldList = Arrays.stream(aClass.getDeclaredFields()).map(Field::getName).toList();
            modelsWithFieldsMap.put(aClass.getSimpleName(), fieldList);
        }
        return modelsWithFieldsMap;
    }
}
