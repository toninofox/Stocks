package org.demo.stock.dto;

import java.util.Date;

public class Trade {
	public Date timestamp;
	public Integer quantity;
	public Indicator indicator;
	public Double price;

	public Trade(Date timestamp, Integer quantity, Indicator indicator, Double price) {
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.indicator = indicator;
		this.price = price;
	}
	

}
