package ru.idea.test.spring.core;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.idea.test.spring.core.component.transaction.TransactionRepo;
import ru.idea.test.spring.core.conf.AnyTransactionalConfig;

public class SpringTransactionTest {

    @Test
    public void shouldLoadAnyAopConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnyTransactionalConfig.class);

        TransactionRepo anyBean =  applicationContext.getBean(TransactionRepo.class);
        var d = anyBean.getData();
        System.out.println(d);
//        anyBean.shouldFail();
//        anyBean.timeout();

    }
}
