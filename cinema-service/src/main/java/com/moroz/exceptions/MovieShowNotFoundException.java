package com.moroz.exceptions;

public class MovieShowNotFoundException extends RuntimeException {
    public MovieShowNotFoundException() {
        super();
    }

    public MovieShowNotFoundException(String message) {
        super(message);
    }

    public MovieShowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieShowNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MovieShowNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
