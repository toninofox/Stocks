package org.demo.stock.dm;

public class TradeManagerFactory {

	private static TradeManager instance = null;
	
	public static ITradeManager getInstance() {
		if(instance == null){
			instance = new TradeManager();
		}
		return instance;
	}

}
