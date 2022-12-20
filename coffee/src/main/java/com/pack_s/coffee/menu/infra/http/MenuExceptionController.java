package com.pack_s.coffee.menu.infra.http;

import com.pack_s.coffee.menu.exception.NotFoundMenuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MenuExceptionController {

    @ExceptionHandler(value = NotFoundMenuException.class)
    public ResponseEntity<String> notFoundMenuException(NotFoundMenuException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
