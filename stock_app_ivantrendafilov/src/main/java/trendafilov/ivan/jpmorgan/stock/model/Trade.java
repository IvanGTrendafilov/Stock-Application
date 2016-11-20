package trendafilov.ivan.jpmorgan.stock.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import trendafilov.ivan.jpmorgan.stock.enums.TradeIndicator;

public class Trade {

	private Stock stock;
	private Date timestamp;
	private BigInteger quantity;
	private TradeIndicator tradeIndicator;
	private BigDecimal price;

	public Trade(final Stock stock, final Date timestamp, final BigInteger quantity,
			final TradeIndicator tradeIndicator, final BigDecimal price) {
		this.stock = stock;
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.tradeIndicator = tradeIndicator;
		this.price = price;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(final Stock stock) {
		this.stock = stock;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}

	public BigInteger getQuantity() {
		return quantity;
	}

	public void setQuantity(final BigInteger quantity) {
		this.quantity = quantity;
	}

	public TradeIndicator getTradeIndicator() {
		return tradeIndicator;
	}

	public void setTradeIndicator(final TradeIndicator tradeIndicator) {
		this.tradeIndicator = tradeIndicator;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((tradeIndicator == null) ? 0 : tradeIndicator.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Trade other = (Trade) obj;
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (quantity == null) {
			if (other.quantity != null) {
				return false;
			}
		} else if (!quantity.equals(other.quantity)) {
			return false;
		}
		if (stock == null) {
			if (other.stock != null) {
				return false;
			}
		} else if (!stock.equals(other.stock)) {
			return false;
		}
		if (timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (tradeIndicator != other.tradeIndicator) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Trade [stock=" + stock + ", timestamp=" + timestamp + ", quantity=" + quantity + ", tradeIndicator="
				+ tradeIndicator + ", price=" + price + "]";
	}

}
