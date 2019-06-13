package com.moneymanager;

import com.moneymanager.balance.BalanceService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AccountSeed {

    @PostConstruct
    public void seed(){
        BalanceService.seedAccounts(15);
    }
}
