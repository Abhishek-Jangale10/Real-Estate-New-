package com.realestate.exception;

public class UserDisabledException extends Throwable {
    public UserDisabledException(String userIsDisabled) {
        super(userIsDisabled);
    }
}
