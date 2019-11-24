package ru.idea.test.spring.boot.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import ru.idea.test.spring.boot.scope.bean.MessageGenerator;

@Configuration
@ComponentScan("ru.idea.test.spring.boot.scope")
public class ScopeConfig {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MessageGenerator requestScopedBean() {
        return new MessageGenerator();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MessageGenerator sessionScopedBean() {
        return new MessageGenerator();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MessageGenerator applicationScopedBean() {
        return new MessageGenerator();
    }
}
