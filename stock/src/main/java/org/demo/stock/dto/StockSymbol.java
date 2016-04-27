package org.demo.stock.dto;

public enum StockSymbol {

	TEA("TEA"),
	POP("POP"),
	ALE("ALE"),
	GIN("GIN"),
	JOE("JOE");
	
	private String symbol;
	
	private StockSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
	public String getValue(){
		return symbol;
	}
	
}
