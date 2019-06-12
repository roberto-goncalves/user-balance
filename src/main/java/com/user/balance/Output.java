package com.user.balance;

public class Output {



    private String message;
    private UserTransaction userTransaction;

    public Output(String message, UserTransaction userTransaction) {
        this.message = message;
        this.userTransaction = userTransaction;
    }

    public UserTransaction getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
