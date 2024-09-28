package com.MyDelight.exception;

public class DelightException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public DelightException(String message) {
		super(message);
	}
	
	public DelightException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

}
