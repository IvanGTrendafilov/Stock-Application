package trendafilov.ivan.jpmorgan.stock.model;

import java.math.BigDecimal;

import trendafilov.ivan.jpmorgan.stock.enums.StockType;

public class Stock {

	private final String stockSymbol;
	private final StockType stockType;
	private final BigDecimal lastDividend;
	private final BigDecimal fixedDividend;
	private final BigDecimal parValue;

	public Stock(final String stockSymbol, final StockType stockType, final BigDecimal lastDividend,
			final BigDecimal fixedDividend, final BigDecimal parValue) {
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fixedDividend == null) ? 0 : fixedDividend.hashCode());
		result = prime * result + ((lastDividend == null) ? 0 : lastDividend.hashCode());
		result = prime * result + ((parValue == null) ? 0 : parValue.hashCode());
		result = prime * result + ((stockSymbol == null) ? 0 : stockSymbol.hashCode());
		result = prime * result + ((stockType == null) ? 0 : stockType.hashCode());
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
		final Stock other = (Stock) obj;
		if (fixedDividend == null) {
			if (other.fixedDividend != null) {
				return false;
			}
		} else if (!fixedDividend.equals(other.fixedDividend)) {
			return false;
		}
		if (lastDividend == null) {
			if (other.lastDividend != null) {
				return false;
			}
		} else if (!lastDividend.equals(other.lastDividend)) {
			return false;
		}
		if (parValue == null) {
			if (other.parValue != null) {
				return false;
			}
		} else if (!parValue.equals(other.parValue)) {
			return false;
		}
		if (stockSymbol == null) {
			if (other.stockSymbol != null) {
				return false;
			}
		} else if (!stockSymbol.equals(other.stockSymbol)) {
			return false;
		}
		if (stockType != other.stockType) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Stock [stockSymbol=" + stockSymbol + ", stockType=" + stockType + ", lastDividend=" + lastDividend
				+ ", fixedDividend=" + fixedDividend + ", parValue=" + parValue + "]";
	}

}
