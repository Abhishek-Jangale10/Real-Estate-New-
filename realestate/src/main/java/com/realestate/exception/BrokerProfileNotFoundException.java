package com.realestate.exception;

public class BrokerProfileNotFoundException extends RuntimeException {
    public BrokerProfileNotFoundException(String message) {
        super(message);
    }
}
