package org.demo.stock.dto;

public class Stock {
	
	public String stockSymbol;
	public String type;
	public Integer lastDividend;
	public Double fixedDividend;
	public Integer perValue;
	
	public Stock(){
		this("","",null,null,null);
	}
	
	public Stock(String stockSymbol, String type, Integer lastDividend, Double fixedDividend, Integer perValue) {
		this.stockSymbol = stockSymbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.perValue = perValue;
	}
	
	

}
