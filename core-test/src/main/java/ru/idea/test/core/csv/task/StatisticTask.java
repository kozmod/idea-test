package ru.idea.test.core.csv.task;

public interface StatisticTask<T, R> {

    void apply(T t);

    R getResult();

    String getName();
}
