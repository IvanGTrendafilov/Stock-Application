package trendafilov.ivan.jpmorgan.stock.services.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trendafilov.ivan.jpmorgan.stock.exceptions.StockServiceException;
import trendafilov.ivan.jpmorgan.stock.model.Stock;
import trendafilov.ivan.jpmorgan.stock.model.Trade;
import trendafilov.ivan.jpmorgan.stock.repositories.StockRepository;
import trendafilov.ivan.jpmorgan.stock.services.StockService;

@Service
public class StockServiceImpl implements StockService {

	private static final int SCALE = 6;

	private final StockRepository stockRepository;

	@Autowired
	public StockServiceImpl(final StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public BigDecimal calculateDividendYield(final String stockSymbol, final BigDecimal price)
			throws StockServiceException {
		final Stock stock = getStockBySymbol(stockSymbol);
		isEqualOrLessThanZero(price);
		BigDecimal result = BigDecimal.ZERO;
		switch (stock.getStockType()) {
		case COMMON:
			result = stock.getLastDividend().divide(price, SCALE, BigDecimal.ROUND_HALF_DOWN);
			break;
		case PREFFERED:
			final BigDecimal fixedDividend = checkForNullBigDecimal(stock.getFixedDividend());
			result = fixedDividend.multiply(stock.getParValue()).divide(price, SCALE, BigDecimal.ROUND_HALF_DOWN);
			break;
		}

		return result.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

	private Stock getStockBySymbol(final String stockSymbol) {
		final Stock stock = stockRepository.getStockBySymbol(stockSymbol);
		if (stock == null) {
			throw new StockServiceException("No such stock by given symbol: " + stockSymbol);
		}
		return stock;
	}

	private void isEqualOrLessThanZero(final BigDecimal bigDecimal) {
		if (bigDecimal == null || BigDecimal.ZERO.compareTo(bigDecimal) >= 0) {
			throw new StockServiceException("The given bigDecimal " + bigDecimal + " is less or equal than zero! ");
		}
	}

	private BigDecimal checkForNullBigDecimal(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			bigDecimal = new BigDecimal("0.00");
		}
		return bigDecimal;
	}

	@Override
	public BigDecimal calculatePriceEarningsRatio(final String stockSymbol, final BigDecimal price)
			throws StockServiceException {
		isEqualOrLessThanZero(price);
		BigDecimal result = BigDecimal.ZERO;
		result = price.divide(calculateDividendYield(stockSymbol, price), SCALE, BigDecimal.ROUND_HALF_DOWN);
		return result.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

	@Override
	public void recordTrade(final Trade trade) throws StockServiceException {
		if (trade == null) {
			throw new StockServiceException("Trade can not be null!");
		}

		if (trade.getStock() == null) {
			throw new StockServiceException("This trade: " + trade + " doesn't have a stock!");
		}

		if (trade.getQuantity().signum() <= 0) {
			throw new StockServiceException("This quantity: " + trade.getQuantity() + " of trade is negative or zero!");
		}

		isEqualOrLessThanZero(trade.getPrice());
		try {
			stockRepository.saveTrade(trade);
		} catch (final Exception e) {
			throw new StockServiceException(e.getMessage());
		}
	}

	@Override
	public BigDecimal calculateStockPriceInPastTime(final long time) throws StockServiceException {
		final Map<Stock, Trade> tradesByTime = stockRepository.getTradesByTime(time);
		if (tradesByTime.isEmpty()) {
			throw new StockServiceException("There isn't recorded trades in past " + time + " miliseconds!");
		}
		BigDecimal price = BigDecimal.ZERO;
		BigInteger quantity = BigInteger.ZERO;
		for (final Stock stock : tradesByTime.keySet()) {
			final Trade trade = tradesByTime.get(stock);
			price = price.add(trade.getPrice().multiply(new BigDecimal(trade.getQuantity())));
			quantity = quantity.add(trade.getQuantity());
		}
		BigDecimal result = BigDecimal.ZERO;
		result = price.divide(new BigDecimal(quantity), SCALE, BigDecimal.ROUND_HALF_DOWN);
		return result.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

	@Override
	public BigDecimal calculateGBCE() {
		// I didn't find anything for this GBCE and I am not sure what exactly
		// is this by specification
		return null;
	}
}
