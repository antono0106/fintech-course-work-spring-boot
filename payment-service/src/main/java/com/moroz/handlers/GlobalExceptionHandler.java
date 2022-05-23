package com.moroz.handlers;

import com.moroz.exceptions.PaymentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("SERVER_ERROR", details);
        log.error("Handled exception", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(PaymentNotFoundException ex,
                                                                           WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("RECORD_NOT_FOUND", details);
        log.error("Handled exception", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}