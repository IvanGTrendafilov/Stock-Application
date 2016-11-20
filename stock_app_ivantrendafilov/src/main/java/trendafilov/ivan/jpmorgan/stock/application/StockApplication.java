package trendafilov.ivan.jpmorgan.stock.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import trendafilov.ivan.jpmorgan.stock.config.StockAppConfig;

@SpringBootApplication
public class StockApplication {
	public static void main(final String[] args) {
		SpringApplication.run(StockAppConfig.class, args);
	}
}
