package org.demo.stock.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.demo.stock.dto.Stock;
import org.demo.stock.dto.StockSymbol;
import org.demo.stock.dto.TypeConstants;
import org.junit.Test;

public class GlobalBeverageCorporateExchangeTest {

	@Test
	public final void getDummyDataShouldContainCorrectType() {
		IExchangeRepository exchange = new GlobalBeverageCorporateExchange();
		
		Stock stock = exchange.getStockBySymbol(StockSymbol.ALE);
		assertThat(stock.type, equalTo(TypeConstants.COMMON));
	}
	
	@Test
	public final void getDummyDataShouldContainCorrectTypeForGIN() {
		IExchangeRepository exchange = new GlobalBeverageCorporateExchange();
		
		Stock stock = exchange.getStockBySymbol(StockSymbol.GIN);
		assertThat(stock.type, equalTo(TypeConstants.PREFERRED));
	}
	
	
}
