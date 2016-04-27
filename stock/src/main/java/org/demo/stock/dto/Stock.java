package org.demo.stock.dto;

public class Stock {
	
	public StockSymbol stockSymbol;
	public String type;
	public Integer lastDividend;
	public Double fixedDividend;
	public Integer perValue;
	
	public Stock(StockSymbol stockSymbol, String type, Integer lastDividend, Double fixedDividend, Integer perValue) {
		this.stockSymbol = stockSymbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.perValue = perValue;
	}


}
