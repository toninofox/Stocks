package org.demo.stock.dao;

import org.demo.stock.dto.Stock;
import org.demo.stock.dto.StockSymbol;
import org.demo.stock.dto.TypeConstants;

class GlobalBeverageCorporateExchange implements IExchangeRepository {

	
	@Override
	public Stock getStockBySymbol(StockSymbol symbol){
		switch (symbol) {
		case TEA:
			return new Stock(StockSymbol.TEA,TypeConstants.COMMON,0,null,100);
		case POP:
			return new Stock(StockSymbol.POP,TypeConstants.COMMON,8,null,100);
		case ALE:
			return new Stock(StockSymbol.ALE,TypeConstants.COMMON,23,null,60);
		case GIN:
			return new Stock(StockSymbol.GIN,TypeConstants.PREFERRED,8,0.02,100);
		case JOE:
			return new Stock(StockSymbol.JOE,TypeConstants.COMMON,13,null,250);		
		default:
			throw new RuntimeException("Stock not found for symbol: "+symbol);
		}
	}
}
