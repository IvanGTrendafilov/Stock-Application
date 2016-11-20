package trendafilov.ivan.jpmorgan.stock.repositories;

import java.util.Map;

import trendafilov.ivan.jpmorgan.stock.model.Stock;
import trendafilov.ivan.jpmorgan.stock.model.Trade;

public interface StockRepository {

	Stock getStockBySymbol(String symbol);

	Map<String, Stock> getAllStocks();

	void saveTrade(Trade trade);

	Map<Stock, Trade> getAllTrades();

	Map<Stock, Trade> getTradesByTime(final long milis);
}
