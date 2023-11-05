package com.mikayelovich.premium_indicators.core.controllers;

import com.mikayelovich.premium_indicators.core.utils.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ModelController {

    @GetMapping("/allModels")
    public ResponseEntity<Map<String, List<String>>> getAllModels() {
        return ResponseEntity.ok(ReflectionUtils.MODELS_WITH_FIELDS);
    }

}
