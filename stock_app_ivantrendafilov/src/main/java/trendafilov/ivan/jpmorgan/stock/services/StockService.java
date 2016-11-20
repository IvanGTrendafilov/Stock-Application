package trendafilov.ivan.jpmorgan.stock.services;

import java.math.BigDecimal;

import trendafilov.ivan.jpmorgan.stock.exceptions.StockServiceException;
import trendafilov.ivan.jpmorgan.stock.model.Trade;

public interface StockService {

	/**
	 * For a given stock calculate the dividend yield
	 * 
	 * @param stockSymbol
	 * @param price
	 * @return Calculated dividend Yield
	 * @throws StockServiceException
	 */
	BigDecimal calculateDividendYield(String stockSymbol, BigDecimal price) throws StockServiceException;

	/**
	 * For a given stock calculate price earning ratio
	 * 
	 * @param stockSymbol
	 * @param price
	 * @return Calculated price earning ratio
	 * @throws StockServiceException
	 */
	BigDecimal calculatePriceEarningsRatio(String stockSymbol, BigDecimal price) throws StockServiceException;

	/**
	 * Record {@link Trade} into database
	 * 
	 * @param trade
	 * @throws StockServiceException
	 */
	void recordTrade(Trade trade) throws StockServiceException;

	/**
	 * Get all {@link Trade} in last time provided by user and calculate stock
	 * price. For example if we want to calculate it for past 15 minutes provide
	 * 15*60*1000
	 * 
	 * @param time
	 * @return Calculated Stock price
	 * @throws StockServiceException
	 */
	BigDecimal calculateStockPriceInPastTime(long time) throws StockServiceException;

	BigDecimal calculateGBCE();
}
