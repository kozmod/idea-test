package ru.idea.test.core.csv.task;

import org.apache.commons.csv.CSVRecord;

import java.util.HashSet;
import java.util.Set;

public class CountUniqueId<T extends Enum<T>> implements StatisticTask<CSVRecord, Integer> {

    private final T headerValue;
    private final Set<String> quantity = new HashSet<>();

    public CountUniqueId(T headerValue) {
        this.headerValue = headerValue;
    }

    @Override
    public void apply(CSVRecord record) {
        quantity.add(
                record.get(headerValue)
        );
    }

    @Override
    public Integer getResult() {
        return quantity.size();
    }

    @Override
    public String getName() {
        return null;
    }
}
