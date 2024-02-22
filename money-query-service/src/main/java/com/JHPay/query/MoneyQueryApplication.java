package com.JHPay.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.JHPay.common", "com.JHPay.query"})
public class MoneyQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyQueryApplication.class, args);
	}

}
