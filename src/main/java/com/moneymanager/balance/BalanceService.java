package com.moneymanager.balance;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

import com.moneymanager.i18n.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymanager.balance.exception.NotEnoughBalanceException;
import com.moneymanager.balance.exception.UserNotFoundException;

@Service
public class BalanceService {

    private static final ConcurrentHashMap<Integer, UserBalance> USERS_ACCOUNTS = new ConcurrentHashMap<>();

    @Autowired
	Message message;

    public UserBalance retrieve(final Integer userId) {
    	if (USERS_ACCOUNTS.containsKey(userId)) {
    		return USERS_ACCOUNTS.get(userId);
    	}
    	throw new UserNotFoundException(message.get("failure.userNotFound"));
    }
    
	public BigDecimal performTransaction(final Integer userId, final BigDecimal transactionAmount) {
		final UserBalance balance = this.retrieve(userId);
		if (balance == null) {
			throw  new UserNotFoundException(message.get("failure.userNotFound"));
		}
        this.validateTransaction(balance, transactionAmount);
        final BigDecimal newBalance = this.getNewBalance(balance, transactionAmount);
        balance.setBalance(newBalance);
        return newBalance;
	}
	
    private void validateTransaction(final UserBalance balance , final BigDecimal transactionAmount) {
        final BigDecimal newBalance = this.getNewBalance(balance, transactionAmount);
    	if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
    		throw new NotEnoughBalanceException(message.get("failure.zeroBalance"));
    	}
    }
    
    private BigDecimal getNewBalance(final UserBalance balance , final BigDecimal transactionAmount) {
    	return balance.getBalance().add(transactionAmount);
    }

    public static void seedAccounts(Integer amount){
    	for(int i = 0; i < amount; i++){
    		USERS_ACCOUNTS.put(i,new UserBalance(i));
		}
	}
}
