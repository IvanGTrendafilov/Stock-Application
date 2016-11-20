package trendafilov.ivan.jpmorgan.stock.database;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import trendafilov.ivan.jpmorgan.stock.enums.StockType;
import trendafilov.ivan.jpmorgan.stock.model.Stock;

@Component
public class StockDB {

	private static Map<String, Stock> stockMap;

	static {
		stockMap = new ConcurrentHashMap<>();
		final Stock firstStock = new Stock("TEA", StockType.COMMON, new BigDecimal("0.00"), null,
				new BigDecimal("100.00"));
		final Stock secondStock = new Stock("POP", StockType.COMMON, new BigDecimal("8.00"), null,
				new BigDecimal("100.00"));
		final Stock thirdStock = new Stock("ALE", StockType.COMMON, new BigDecimal("23.00"), null,
				new BigDecimal("60.00"));
		final Stock forthStock = new Stock("GIN", StockType.PREFFERED, new BigDecimal("8.00"), new BigDecimal("0.02"),
				new BigDecimal("100.00"));
		final Stock fifthStock = new Stock("JOE", StockType.COMMON, new BigDecimal("13.00"), null,
				new BigDecimal("250.00"));
		stockMap.put(firstStock.getStockSymbol(), firstStock);
		stockMap.put(secondStock.getStockSymbol(), secondStock);
		stockMap.put(thirdStock.getStockSymbol(), thirdStock);
		stockMap.put(forthStock.getStockSymbol(), forthStock);
		stockMap.put(fifthStock.getStockSymbol(), fifthStock);
		stockMap = Collections.unmodifiableMap(stockMap);
	}

	public Stock getStockBySymbol(final String symbol) {
		return stockMap.get(symbol);
	}

	public Map<String, Stock> getAllStocks() {
		return stockMap;
	}
}
