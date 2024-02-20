package com.JHPay.moneyAggregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.JHPay.common", "com.JHPay.moneyAggregation"})
public class MoneyAggregationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyAggregationApplication.class, args);
	}

}
