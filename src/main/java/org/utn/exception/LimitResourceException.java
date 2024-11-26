package org.utn.exception;

public class LimitResourceException extends RuntimeException {
    public LimitResourceException(String message) {
        super(message);
    }
}
