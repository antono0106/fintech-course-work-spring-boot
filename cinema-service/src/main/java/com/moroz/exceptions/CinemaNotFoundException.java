package com.moroz.exceptions;

public class CinemaNotFoundException extends RuntimeException {
    public CinemaNotFoundException() {
        super();
    }

    public CinemaNotFoundException(String message) {
        super(message);
    }

    public CinemaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CinemaNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CinemaNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
