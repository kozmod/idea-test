package ru.idea.test.spring.core.component;

public interface Delegate<T> {

    void execute(T val);
}
