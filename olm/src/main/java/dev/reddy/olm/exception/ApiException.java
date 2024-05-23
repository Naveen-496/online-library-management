package dev.reddy.olm.exception;

public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}
