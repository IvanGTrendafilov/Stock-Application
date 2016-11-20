package trendafilov.ivan.jpmorgan.stock.services;

import java.math.BigDecimal;

import trendafilov.ivan.jpmorgan.stock.exceptions.StockServiceException;

public interface StockService {

	BigDecimal calculateDividendYield(String stockSymbol, BigDecimal price) throws StockServiceException;

	BigDecimal calculatePriceEarningsRatio(String stockSymbol, BigDecimal price) throws StockServiceException;
}
