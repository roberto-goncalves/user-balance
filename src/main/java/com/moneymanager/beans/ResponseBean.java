package com.moneymanager.beans;

public class ResponseBean<T> {
    private String message;
    private T data;

    public ResponseBean(String message, T data) {
        this.message = message;
        this.data = data;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
