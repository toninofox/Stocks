package org.demo.stock.dao;

public class ExchangeRepositoryFactory {

	
	public IExchangeRepository getGlobalBeverage(){
		return new GlobalBeverageCorporateExchange();
	}
}
