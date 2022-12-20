package com.pack_s.mijung_noodles.order.infra.http;

import com.pack_s.mijung_noodles.order.exception.NotFoundOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionController {

    @ExceptionHandler(value = NotFoundOrderException.class)
    public ResponseEntity<String> notFoundOrderException(NotFoundOrderException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
