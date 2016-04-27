package org.demo.stock.dm;

import java.util.Collection;

import org.demo.stock.dto.Stock;
import org.demo.stock.dto.StockSymbol;
import org.demo.stock.dto.Trade;
import org.demo.stock.dto.TypeConstants;

public class StockUtility {
	
	private ITradeManager tradeManager;

	StockUtility(){
		this(TradeManagerFactory.getInstance());
	}

	StockUtility(ITradeManager tradeManager) {
		this.tradeManager = tradeManager;
	}

	public Double calculateDividendYield(Stock stock){
		if(stock.type.equals(TypeConstants.COMMON)){
			return stock.lastDividend/calculateStockPrice(stock.stockSymbol);
		} else if(stock.type.equals(TypeConstants.PREFERRED)){
			return stock.fixedDividend*stock.perValue/calculateStockPrice(stock.stockSymbol);
		} else {
			throw new RuntimeException("Stock Type "+stock.type+" is not a valid one. It is not possible to calculate Dividend Yield");
		}
	}
	
	public Double calculateStockPrice(StockSymbol stock){
		Collection<Trade> trades = tradeManager.getTradesBy(stock);
		Double sum = 0.0;
		Double qtySum = 0.0;
		for (Trade trade : trades) {
			sum += trade.quantity*trade.price;
			qtySum += trade.quantity;
		}
		return sum/qtySum;
	}
	
	
	public Double calculatePERatio(Stock stock){
		return calculateStockPrice(stock.stockSymbol)/calculateDividendYield(stock);
	}
	
	
	public Double calculateGeometricMean(Stock stock){
		StockSymbol[] values = StockSymbol.values();
		Double multiplier = 1.0;
		int countOfPrices = 0;
		for (StockSymbol stockSymbol : values) {
			Collection<Trade> tradesBy = tradeManager.getTradesBy(stockSymbol);
			for (Trade trade : tradesBy) {
				multiplier *= trade.price;
				countOfPrices++;
			}
		}
		
		return Math.pow(multiplier, 1/countOfPrices);
		
	}
}
