package trendafilov.ivan.jpmorgan.stock.repositories.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import trendafilov.ivan.jpmorgan.stock.database.StockDB;
import trendafilov.ivan.jpmorgan.stock.model.Stock;
import trendafilov.ivan.jpmorgan.stock.repositories.StockRepository;

@Repository
public class StockRepositoryImpl implements StockRepository {

	private final StockDB stockDB;

	@Autowired
	public StockRepositoryImpl(final StockDB stockDB) {
		this.stockDB = stockDB;
	}

	@Override
	public Stock getStockBySymbol(final String symbol) {
		return stockDB.getStockBySymbol(symbol);
	}

	@Override
	public Map<String, Stock> getAllStocks() {
		return stockDB.getAllStocks();
	}
}
