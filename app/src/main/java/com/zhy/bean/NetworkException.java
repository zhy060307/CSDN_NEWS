package com.zhy.bean;

public class NetworkException extends Exception {
    private String message;

    public NetworkException(String message) {
        super(message);
        this.message = message;
    }
}
