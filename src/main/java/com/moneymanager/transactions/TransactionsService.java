package com.moneymanager.transactions;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymanager.balance.BalanceService;

@Service
public class TransactionsService {

	@Autowired private BalanceService balanceService;

	private static final ConcurrentHashMap<Integer, CopyOnWriteArrayList<UserTransaction>> USERS_TRANSACTIONS = new ConcurrentHashMap<>();
	
    public BigDecimal insert(UserTransaction tx){
       final BigDecimal newBalance = this.balanceService.performTransaction(tx.getUserId(), tx.getAmount());
       if (USERS_TRANSACTIONS.containsKey(tx.getUserId())) {
    	   USERS_TRANSACTIONS.get(tx.getUserId()).add(tx);
       } else {
    	   final CopyOnWriteArrayList<UserTransaction> newHistory = new CopyOnWriteArrayList<>();
    	   newHistory.add(tx);
    	   USERS_TRANSACTIONS.put(tx.getUserId(), newHistory);
       }
       
       return newBalance;
    }

}
