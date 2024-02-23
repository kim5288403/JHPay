package com.JHPay.settlement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.JHPay.common", "com.JHPay.settlement"})
public class SettlementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettlementApplication.class, args);
	}

}
