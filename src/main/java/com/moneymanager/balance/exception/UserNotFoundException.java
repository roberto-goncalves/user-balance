package com.moneymanager.balance.exception;

import com.moneymanager.exception.MoneyManagerException;
import com.moneymanager.i18n.Messages;

public class UserNotFoundException extends MoneyManagerException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super(Messages.FAILURE_USER);
	}
}
