package stock_app_ivantrendafilov.stock_app_ivantrendafilov;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import trendafilov.ivan.jpmorgan.stock.enums.StockType;
import trendafilov.ivan.jpmorgan.stock.enums.TradeIndicator;
import trendafilov.ivan.jpmorgan.stock.model.Stock;
import trendafilov.ivan.jpmorgan.stock.model.Trade;
import trendafilov.ivan.jpmorgan.stock.repositories.StockRepository;
import trendafilov.ivan.jpmorgan.stock.services.StockService;
import trendafilov.ivan.jpmorgan.stock.services.impl.StockServiceImpl;

public class StockServiceTest {

	private StockRepository stockRepository;
	private StockService stockService;

	@Before
	public void before() {
		stockRepository = Mockito.mock(StockRepository.class);
		stockService = new StockServiceImpl(stockRepository);
	}

	@After
	public void after() {
		stockRepository = null;
		stockService = null;
	}

	@Test
	public void should_CalculatePriceTrade() {
		final Stock stock = createStock();
		final Trade trade = createTradeByStock(stock);
		final Map<Stock, Trade> tradeMap = new ConcurrentHashMap<>();
		tradeMap.put(stock, trade);
		Mockito.when(stockRepository.getTradesByTime(15 * 60 * 1000)).thenReturn(tradeMap);
		final BigDecimal calculateStockPriceInPastTime = stockService.calculateStockPriceInPastTime(15 * 60 * 1000);
		org.junit.Assert.assertTrue(calculateStockPriceInPastTime != null);

	}

	private Stock createStock() {
		return new Stock("JOE", StockType.COMMON, new BigDecimal("13.00"), null, new BigDecimal("250.00"));
	}

	private Trade createTradeByStock(final Stock stock) {
		return new Trade(stock, new Date(), BigInteger.valueOf(2), TradeIndicator.BUY, new BigDecimal("2.00"));
	}

	@Test
	public void should_RecordTrade() {
		final Stock stock = createStock();
		final Trade trade = createTradeByStock(stock);
		final ArgumentCaptor<Trade> argumentCaptor = ArgumentCaptor.forClass(Trade.class);
		stockService.recordTrade(trade);
		Mockito.verify(stockRepository, Mockito.times(1)).saveTrade(argumentCaptor.capture());
		Assert.assertEquals(argumentCaptor.getValue().getStock(), trade.getStock());
	}

}
