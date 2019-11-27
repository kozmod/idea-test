package ru.idea.test.core.csv.task;

import org.apache.commons.csv.CSVRecord;

import java.util.HashMap;
import java.util.Map;

public class CountUniqueVal<T extends Enum<T>> implements StatisticTask<CSVRecord, Map<String, Integer>> {

    private final T headerValue;
    private final Map<String, Integer> valueQuantity = new HashMap<>();

    public CountUniqueVal(T headerValue) {
        this.headerValue = headerValue;
    }

    @Override
    public void apply(CSVRecord record) {
        String recordValue = record.get(headerValue);
        if (!recordValue.isBlank()) {
            Integer quantity = valueQuantity.computeIfAbsent(recordValue, k -> 0);
            valueQuantity.put(recordValue, ++quantity);
        }
    }

    @Override
    public Map<String, Integer> getResult() {
        return valueQuantity;
    }

    @Override
    public String getName() {
        return null;
    }
}
