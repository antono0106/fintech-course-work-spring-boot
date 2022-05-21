package com.moroz.exceptions;

public class OccupiedRowAndPlaceException extends RuntimeException {
    public OccupiedRowAndPlaceException() {
    }

    public OccupiedRowAndPlaceException(String message) {
        super(message);
    }

    public OccupiedRowAndPlaceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OccupiedRowAndPlaceException(Throwable cause) {
        super(cause);
    }

    public OccupiedRowAndPlaceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
