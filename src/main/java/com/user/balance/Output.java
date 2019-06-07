package com.user.balance;

public class Output {

    private String message;
    private UserTransaction userTransaction;

    public Output(String message, UserTransaction userTransaction) {
        this.message = message;
        this.userTransaction = userTransaction;
    }
}
