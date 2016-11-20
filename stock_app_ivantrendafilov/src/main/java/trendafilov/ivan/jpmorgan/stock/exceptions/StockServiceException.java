package trendafilov.ivan.jpmorgan.stock.exceptions;

public class StockServiceException extends RuntimeException {

	private static final long serialVersionUID = 4236338459440862041L;
	private final String message;

	public StockServiceException(final String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
