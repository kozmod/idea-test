package ru.idea.test.spring.core.entity;

import java.util.StringJoiner;


public class AnyBean {

    private String value;

    public String getValue() {
        return value;
    }

    public AnyBean setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AnyBean.class.getSimpleName() + "[", "]")
                .add("value='" + value + "'")
                .toString();
    }
}
