package org.demo.stock.dao;

import org.demo.stock.dto.Stock;
import org.demo.stock.dto.StockSymbol;

public interface IExchangeRepository {

	public Stock getStockBySymbol(StockSymbol symbol);

}
