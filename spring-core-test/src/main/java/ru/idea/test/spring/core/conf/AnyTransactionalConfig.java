package ru.idea.test.spring.core.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("ru.idea.test.spring.core.component.transaction")
@EnableTransactionManagement
public class AnyTransactionalConfig {
}
