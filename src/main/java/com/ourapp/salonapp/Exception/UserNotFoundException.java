package com.ourapp.salonapp.Exception;

public class UserNotFoundException extends Exception  {

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String value)
	{
	   super(value);	
	}

}
