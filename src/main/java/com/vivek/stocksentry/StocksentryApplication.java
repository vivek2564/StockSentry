package com.vivek.stocksentry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StocksentryApplication {
	public static void main(String[] args) {
		SpringApplication.run(StocksentryApplication.class, args);
	}
}