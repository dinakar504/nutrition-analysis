package com.dao;

@SuppressWarnings("serial")
public class OrderNotExists extends Exception{
	public OrderNotExists(String err)
	{
		super(err);
	}
}
