package ru.idea.test.spring.core.rife_interview.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceB {
    @Autowired
    private ServiceA serviceA;

    @PostConstruct
    void init() {
        System.out.print("2");
    }

    public void print() {
        System.out.print("3");
    }
}
