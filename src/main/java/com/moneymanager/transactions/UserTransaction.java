package com.moneymanager.transactions;

import java.math.BigDecimal;

public class UserTransaction {

    private Integer userId;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private BigDecimal amount;

    public UserTransaction(Integer userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
