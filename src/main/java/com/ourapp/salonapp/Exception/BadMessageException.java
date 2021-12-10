package com.ourapp.salonapp.Exception;

public class BadMessageException extends Exception  {

	private static final long serialVersionUID = 1L;
	
	public BadMessageException(String value)
	{
	   super(value);	
	}

}
