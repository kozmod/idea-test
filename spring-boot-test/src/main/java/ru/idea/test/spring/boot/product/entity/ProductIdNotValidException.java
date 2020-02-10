package ru.idea.test.spring.boot.product.entity;

public class ProductIdNotValidException extends RuntimeException {

    private static final long serialVersionUID = 1234569999909093231L;

    public ProductIdNotValidException() {
        super();
    }

    public ProductIdNotValidException(String message) {
        super(message);
    }
}
