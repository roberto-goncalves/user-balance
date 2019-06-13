package com.moneymanager.balance.exception;

import com.moneymanager.exception.MoneyManagerException;

public class NotEnoughBalanceException extends MoneyManagerException {
	private static final long serialVersionUID = 1L;

	public NotEnoughBalanceException(String message) {
		super(message);
	}
}
