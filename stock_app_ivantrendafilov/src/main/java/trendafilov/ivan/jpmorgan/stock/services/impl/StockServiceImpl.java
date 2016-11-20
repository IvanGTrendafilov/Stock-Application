package trendafilov.ivan.jpmorgan.stock.services.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trendafilov.ivan.jpmorgan.stock.exceptions.StockServiceException;
import trendafilov.ivan.jpmorgan.stock.model.Stock;
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
}
