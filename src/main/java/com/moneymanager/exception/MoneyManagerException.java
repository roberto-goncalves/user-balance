package com.moneymanager.exception;

public class MoneyManagerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MoneyManagerException(String message) {
		super(message);
	}
	public MoneyManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}
