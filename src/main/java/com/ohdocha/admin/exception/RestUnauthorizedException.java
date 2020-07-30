package com.ohdocha.admin.exception;

public class RestUnauthorizedException extends RestKnownException {

    public RestUnauthorizedException(String errorMessage) {
        super(401, 9999, errorMessage);
    }

    public RestUnauthorizedException(int errorCode, String errorMessage) {
        super(401, errorCode, errorMessage);
    }

}
