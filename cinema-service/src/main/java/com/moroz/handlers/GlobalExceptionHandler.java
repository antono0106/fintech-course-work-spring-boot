package com.moroz.handlers;

import com.moroz.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("SERVER_ERROR", details);
        log.error("Handled exception", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex,
                                                                           WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("RECORD_NOT_FOUND", details);
        log.error("Handled exception", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(MovieNotFoundException ex,
                                                                           WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("RECORD_NOT_FOUND", details);
        log.error("Handled exception", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CinemaNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(CinemaNotFoundException ex,
                                                                           WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("RECORD_NOT_FOUND", details);
        log.error("Handled exception", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieShowNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(MovieShowNotFoundException ex,
                                                                           WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("RECORD_NOT_FOUND", details);
        log.error("Handled exception", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(TicketNotFoundException ex,
                                                                           WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("RECORD_NOT_FOUND", details);
        log.error("Handled exception", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OccupiedRowAndPlaceException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(OccupiedRowAndPlaceException ex,
                                                                           WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("RECORD_NOT_FOUND", details);
        log.error("Handled exception", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}