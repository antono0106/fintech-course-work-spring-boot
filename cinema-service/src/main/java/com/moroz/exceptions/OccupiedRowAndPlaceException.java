package com.moroz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
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
