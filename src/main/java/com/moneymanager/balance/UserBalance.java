package com.moneymanager.balance;

import java.math.BigDecimal;

public class UserBalance {

    private Integer userId;
    private BigDecimal balance;

    public UserBalance(Integer userId) {
        this.userId = userId;
        this.balance = BigDecimal.ZERO;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
