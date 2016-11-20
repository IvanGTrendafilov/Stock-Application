package trendafilov.ivan.jpmorgan.stock.repositories;

import java.util.Map;

import trendafilov.ivan.jpmorgan.stock.model.Stock;

public interface StockRepository {

	Stock getStockBySymbol(String symbol);

	Map<String, Stock> getAllStocks();
}
