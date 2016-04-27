package org.demo.stock.dm;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.demo.stock.dto.Indicator;
import org.demo.stock.dto.Stock;
import org.demo.stock.dto.StockSymbol;
import org.demo.stock.dto.Trade;
import org.demo.stock.dto.TypeConstants;
import org.junit.Test;

public class StockUtilityTest {

	@Test
	public final void stockPriceShouldBeCalculatedAsSumOfPriceXQtyDivQties() {	
		
		ITradeManager tradeManager = mock(ITradeManager.class);
		Collection<Trade> list = new ArrayList<>();
		list.add(new Trade(new Date(),1,Indicator.SELL, 1.1));
		list.add(new Trade(new Date(),10,Indicator.SELL, 1.8));
		list.add(new Trade(new Date(),5,Indicator.SELL, 2.1));
		StockSymbol joe = StockSymbol.JOE;
		when(tradeManager.getTradesBy(joe)).thenReturn(list );
		Double stockPrice = new StockUtility(tradeManager).calculateStockPrice(joe);
		assertThat(stockPrice,equalTo((1*1.1+10*1.8+5*2.1)/(1+10+5)));
		
	}
	
	@Test
	public void calculateDividendYieldForCommonTest() throws Exception {
		ITradeManager tradeManager = mock(ITradeManager.class);
		Collection<Trade> list = new ArrayList<>();
		list.add(new Trade(new Date(),1,Indicator.SELL, 1.1));
		list.add(new Trade(new Date(),10,Indicator.SELL, 1.8));
		list.add(new Trade(new Date(),5,Indicator.SELL, 2.1));
		StockSymbol joe = StockSymbol.JOE;
		when(tradeManager.getTradesBy(joe)).thenReturn(list );
		Stock stock = new Stock(joe, TypeConstants.COMMON, 10, 0.12, 100);
		
		StockUtility stockUtility = new StockUtility(tradeManager);
		Double dividend = stockUtility.calculateDividendYield(stock);
		
		assertThat(dividend, equalTo(stock.lastDividend/stockUtility.calculateStockPrice(joe)));
	}
	
	

	@Test
	public void calculateDividendYieldForPreferredTest() throws Exception {
		ITradeManager tradeManager = mock(ITradeManager.class);
		Collection<Trade> list = new ArrayList<>();
		list.add(new Trade(new Date(),1,Indicator.SELL, 1.1));
		list.add(new Trade(new Date(),10,Indicator.SELL, 1.8));
		list.add(new Trade(new Date(),5,Indicator.SELL, 2.1));
		StockSymbol joe = StockSymbol.JOE;
		when(tradeManager.getTradesBy(joe)).thenReturn(list );
		Stock stock = new Stock(joe, TypeConstants.PREFERRED, 10, 0.12, 100);
		
		StockUtility stockUtility = new StockUtility(tradeManager);
		Double dividend = stockUtility.calculateDividendYield(stock);
		
		assertThat(dividend, equalTo(stock.fixedDividend*stock.perValue/stockUtility.calculateStockPrice(stock.stockSymbol)));
	}
	
	@Test
	public void calculateGemoetricMean() throws Exception {
		ITradeManager tradeManager = mock(ITradeManager.class);
		Collection<Trade> list = new ArrayList<>();
		list.add(new Trade(new Date(),1,Indicator.SELL, 1.1));
		list.add(new Trade(new Date(),10,Indicator.SELL, 1.8));
		list.add(new Trade(new Date(),5,Indicator.SELL, 2.1));
		Collection<Trade> list2 = new ArrayList<>();
		list2.add(new Trade(new Date(),1,Indicator.SELL, 2.0));
		list2.add(new Trade(new Date(),10,Indicator.SELL, 3.0));
		list2.add(new Trade(new Date(),5,Indicator.SELL, 4.0));
		StockSymbol joe = StockSymbol.JOE;
		when(tradeManager.getTradesBy(joe)).thenReturn(list );
		when(tradeManager.getTradesBy(StockSymbol.POP)).thenReturn(new ArrayList<Trade>());
		when(tradeManager.getTradesBy(StockSymbol.ALE)).thenReturn(new ArrayList<Trade>());
		when(tradeManager.getTradesBy(StockSymbol.GIN)).thenReturn(list2);
		when(tradeManager.getTradesBy(StockSymbol.TEA)).thenReturn(new ArrayList<Trade>());
		
		StockUtility stockUtility = new StockUtility(tradeManager);
		
		Stock stock = new Stock(joe, TypeConstants.COMMON, 10, 0.12, 100);
		Double geoMean = stockUtility.calculateGeometricMean(stock);
		
		assertThat(geoMean,equalTo(Math.pow((1.1*1.8*2.1*2*3*4),1/6)));
	}

}
