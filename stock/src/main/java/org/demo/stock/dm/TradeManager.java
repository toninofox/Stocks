package org.demo.stock.dm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.demo.stock.dto.StockSymbol;
import org.demo.stock.dto.Trade;

public class TradeManager implements ITradeManager {

	private static Map<StockSymbol, Collection<Trade>> trades = new HashMap<>();
	
	TradeManager() {
		
	}
	
	
	@Override
	public synchronized	void register(StockSymbol symbol, Trade trade){
		if(!trades.containsKey(symbol)){
			trades.put(symbol,new ArrayList<Trade>());
		}
		
		trades.get(symbol).add(trade);
	}
	
	@Override
	public synchronized Collection<Trade> getTradesBy(StockSymbol symbol){
		Collection<Trade> result = new ArrayList<>();
		if(trades.containsKey(symbol)){
			Collection<Trade> tradesBySymbol = trades.get(symbol);

			for (Trade trade : tradesBySymbol) {
				if(isEarlierThan15Minutes(trade)){
					result.add(trade);
				}
			}
		} else {
			//doNothing
		}
		
		return result;
	}

	private boolean isEarlierThan15Minutes(Trade trade) {
		long duration = new Date().getTime() - trade.timestamp.getTime();
		long MAX_DURATION = TimeUnit.MILLISECONDS.convert(15, TimeUnit.MINUTES);
		return duration<=MAX_DURATION;
	}
	
}
