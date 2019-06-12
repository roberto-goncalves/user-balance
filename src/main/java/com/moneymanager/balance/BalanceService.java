package com.moneymanager.balance;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.moneymanager.balance.exception.NotEnoughBalanceException;
import com.moneymanager.balance.exception.UserNotFoundException;

@Service
public class BalanceService {

    private static final ConcurrentHashMap<Integer, UserBalance> USERS_ACCOUNTS = new ConcurrentHashMap<>();
    
    public UserBalance retrieve(final Integer userId) {
    	if (USERS_ACCOUNTS.containsKey(userId)) {
    		return USERS_ACCOUNTS.get(userId);
    	}
    	return null;
    }
    
	public BigDecimal performTransaction(final Integer userId, final BigDecimal transactionAmount) {
		final UserBalance balance = this.retrieve(userId);
		if (balance == null) {
			throw  new UserNotFoundException();
		}
		
        this.validateTransaction(balance, transactionAmount);
        final BigDecimal newBalance = this.getNewBalance(balance, transactionAmount);
        balance.setBalance(newBalance);
        return newBalance;
	}
	
    private void validateTransaction(final UserBalance balance , final BigDecimal transactionAmount) {
        final BigDecimal newBalance = this.getNewBalance(balance, transactionAmount);
    	if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
    		throw new NotEnoughBalanceException();
    	}
    }
    
    private BigDecimal getNewBalance(final UserBalance balance , final BigDecimal transactionAmount) {
    	return balance.getBalance().add(transactionAmount);
    }
}
