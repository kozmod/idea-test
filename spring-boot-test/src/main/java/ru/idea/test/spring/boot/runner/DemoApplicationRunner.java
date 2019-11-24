package ru.idea.test.spring.boot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationRunner implements ApplicationRunner, CommandLineRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(DemoApplicationRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("RUN - DemoApplicationRunner as ApplicationRunner");
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("RUN - DemoApplicationRunner as CommandLineRunner");
    }
}
