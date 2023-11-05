package com.mikayelovich.premium_indicators.core.indicator_processors;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndicatorController {

    @PostMapping("/calculate-indicator")
    public ResponseEntity<Integer> calculateIndicator(@RequestBody IndicatorRequestModel model) {

        return ResponseEntity.ok(1);
    }

}


