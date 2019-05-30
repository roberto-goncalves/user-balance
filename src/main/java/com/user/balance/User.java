package com.user.balance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class User {

    private Integer userId;
    private BigDecimal balance;

    public User(Integer userId) {
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
