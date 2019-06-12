package com.moneymanager.balance.exception;

import com.moneymanager.exception.MoneyManagerException;
import com.moneymanager.i18n.Messages;

public class NotEnoughBalanceException extends MoneyManagerException {
	private static final long serialVersionUID = 1L;

	public NotEnoughBalanceException() {
		super(Messages.FAILURE_ZERO);
	}
}
