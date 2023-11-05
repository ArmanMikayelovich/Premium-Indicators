package com.mikayelovich.premium_indicators.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T>  T jsonToObject(String data, Class<T> clazz) throws JsonProcessingException {

        return MAPPER.readValue(data, clazz);
    }


}
