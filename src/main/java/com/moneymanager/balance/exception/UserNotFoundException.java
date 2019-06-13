package com.moneymanager.balance.exception;

import com.moneymanager.exception.MoneyManagerException;

public class UserNotFoundException extends MoneyManagerException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
