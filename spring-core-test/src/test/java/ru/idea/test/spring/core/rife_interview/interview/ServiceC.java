package ru.idea.test.spring.core.rife_interview.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceC {
    @Autowired
    private ServiceB serviceB;

    @PostConstruct
    void init() {
        serviceB.print();
        System.out.print("4");
    }
}

