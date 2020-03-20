package com.dao;

@SuppressWarnings("serial")
public class InvalidUser extends Exception {
	public InvalidUser(String err)
	{
		super(err);
	}
}
