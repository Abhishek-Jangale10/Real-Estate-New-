package com.realestate.exception;

public class InquiryNotFoundException extends RuntimeException {
    public InquiryNotFoundException(String message) {
        super(message);
    }
}
