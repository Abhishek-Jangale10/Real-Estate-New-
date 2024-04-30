package com.realestate.exception;

public class OtpExpiredException extends Throwable {
    public OtpExpiredException(String otpHasExpired)   {
        super(otpHasExpired);
    }
}
