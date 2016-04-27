package org.demo.stock.dm;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.demo.stock.dto.Indicator;
import org.demo.stock.dto.StockSymbol;
import org.demo.stock.dto.Trade;
import org.junit.Test;

public class TradeManagerTest {

	@Test
	public final void shouldReturnOnlyTradesOfLatest15Minutes() {
		Date now = new Date();
		Calendar d1 = Calendar.getInstance();
		d1.setTime(now);
		d1.set(Calendar.MINUTE, d1.get(Calendar.MINUTE)-20);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(now);
		Calendar d3 = Calendar.getInstance();
		d3.setTime(now);
		d3.set(Calendar.MINUTE, d2.get(Calendar.MINUTE)-14);
		Trade t1 = new Trade(d1.getTime(), 1, Indicator.BUY, 100.0);
		Trade t2 = new Trade(d2.getTime(), 1, Indicator.BUY, 100.0);
		Trade t3 = new Trade(d3.getTime(), 1, Indicator.BUY, 100.0);
		TradeManager tradeManager = new TradeManager();
		tradeManager.register(StockSymbol.ALE, t1);
		tradeManager.register(StockSymbol.ALE, t2);
		tradeManager.register(StockSymbol.ALE, t3);
		
		assertThat(tradeManager.getTradesBy(StockSymbol.ALE), hasSize(2));
		
	}

}
