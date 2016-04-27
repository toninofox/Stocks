package org.demo.stock.dm;

import java.util.Collection;

import org.demo.stock.dto.StockSymbol;
import org.demo.stock.dto.Trade;

public interface ITradeManager {

	public Collection<Trade> getTradesBy(StockSymbol symbol);

	public void register(StockSymbol symbol, Trade trade);

}