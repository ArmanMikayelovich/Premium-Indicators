package com.mikayelovich.premium_indicators.core;

import com.mikayelovich.premium_indicators.core.expressions.CalculationExpression;
import com.mikayelovich.premium_indicators.core.expressions.Formula;
import com.mikayelovich.premium_indicators.core.expressions.MathProcessor;
import com.mikayelovich.premium_indicators.core.trade_unit.ExpressionArgument;
import com.mikayelovich.premium_indicators.core.trade_unit.TradeUnitDTO;
import org.mariuszgromada.math.mxparser.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication

public class PremiumIndicatorsCoreApplication {
	private static List<TradeUnitDTO> trades = new ArrayList<>();
	private static Map<String, List<? extends ExpressionArgument>> parameters;
 static {
	 String s = License.geTermsOfAgreement();
	 License.iConfirmNonCommercialUse(s);
	 for (int i = 0; i < 12; i++) {
		 TradeUnitDTO tradeUnitDTO = new TradeUnitDTO();
		 tradeUnitDTO.setPrice("2" + i);
		 trades.add(tradeUnitDTO);
	 }
	 parameters = new HashMap<>();
	 parameters.put("trades", trades);
 }
	private static final Logger log = LoggerFactory.getLogger(PremiumIndicatorsCoreApplication.class);

	public static void main(String[] args) {
		CalculationExpression calculationExpression = new CalculationExpression(
				new Formula("5 + max(trades.price) / 2", List.of()),
				parameters);

		MathProcessor.process(calculationExpression);

		SpringApplication.run(PremiumIndicatorsCoreApplication.class);
	}

}
