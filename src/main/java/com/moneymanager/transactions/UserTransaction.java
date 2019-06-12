package com.moneymanager.transactions;

import java.math.BigDecimal;

public class UserTransaction {

    private Integer userId;
    private BigDecimal amount;

    public UserTransaction(Integer userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
