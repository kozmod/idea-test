package ru.idea.test.spring.core.component.transaction;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Repository
public class TransactionRepo {

    @Transactional
    public String getData() {
        return "some data";
    }

    @Transactional(rollbackFor=CustomTransactionException.class)
    public String shouldFail() {
       throw new CustomTransactionException("test fail");
    }

    @Transactional(timeout = 3)
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "timout";
        }
        return "NO timout";
    }
}
