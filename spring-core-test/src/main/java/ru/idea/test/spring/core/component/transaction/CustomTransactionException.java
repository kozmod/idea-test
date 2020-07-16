package ru.idea.test.spring.core.component.transaction;

import org.springframework.transaction.TransactionException;

public class CustomTransactionException extends TransactionException {

    public CustomTransactionException(String msg) {
        super(msg);
    }
}
