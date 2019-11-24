package ru.idea.test.spring.boot.scope.bean;

import java.util.StringJoiner;

public class MessageGenerator {
    private String message;

    public String getMessage() {
        return message;
    }

    public MessageGenerator setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MessageGenerator.class.getSimpleName() + "[", "]")
                .add("message='" + message + "'")
                .toString();
    }
}
