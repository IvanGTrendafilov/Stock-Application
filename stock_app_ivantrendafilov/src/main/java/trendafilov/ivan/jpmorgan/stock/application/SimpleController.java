package trendafilov.ivan.jpmorgan.stock.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trendafilov.ivan.jpmorgan.stock.services.StockService;

@RestController
public class SimpleController {

	private final StockService stockService;

	@Autowired
	public SimpleController(final StockService stockService) {
		this.stockService = stockService;
	}

	@RequestMapping("/")
	public void index() {
		System.out.println("This is Rest controller for testing purpose");
	}
}
