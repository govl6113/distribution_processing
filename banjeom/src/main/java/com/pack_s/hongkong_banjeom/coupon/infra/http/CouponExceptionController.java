package com.pack_s.hongkong_banjeom.coupon.infra.http;

import com.pack_s.hongkong_banjeom.coupon.exception.NotFoundCouponException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponExceptionController {

    @ExceptionHandler(value = NotFoundCouponException.class)
    public ResponseEntity<String> notFoundCouponException(NotFoundCouponException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
