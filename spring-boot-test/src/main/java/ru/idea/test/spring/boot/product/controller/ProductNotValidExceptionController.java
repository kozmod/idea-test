package ru.idea.test.spring.boot.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.idea.test.spring.boot.product.entity.ProductIdNotValidException;

@ControllerAdvice
public class ProductNotValidExceptionController {

    @ExceptionHandler(value = ProductIdNotValidException.class)
    public ResponseEntity<Object> exception(ProductIdNotValidException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
