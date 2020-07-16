package ru.idea.test.spring.core.component.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.*;

@Component
public class TransactionManagerBean implements PlatformTransactionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionManagerBean.class);

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
        if(definition == null){
            throw new CustomTransactionException("definition is null");
        }
        LOGGER.info(
                String.format("%s - %s",definition.getClass().toString(), definition.toString())
        );
        return null;
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        LOGGER.info(status.toString());
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        LOGGER.info(status.toString());
    }
}
