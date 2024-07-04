package com.realestate.exception;

public class ServiceBookingNotFoundException extends RuntimeException {
    public ServiceBookingNotFoundException(String message) {
        super(message);
    }
}
