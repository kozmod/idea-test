package ru.idea.test.spring.core.rife_interview.interview;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceA {
    @PostConstruct
    void init() {
        System.out.print("1");

    }
}
