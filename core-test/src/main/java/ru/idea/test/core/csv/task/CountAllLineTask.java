package ru.idea.test.core.csv.task;

import org.apache.commons.csv.CSVRecord;

import java.util.concurrent.atomic.AtomicLong;

public class CountAllLineTask implements StatisticTask<CSVRecord, Long> {

    private final AtomicLong quantity = new AtomicLong(0);

    @Override
    public void apply(CSVRecord strings) {
        quantity.getAndIncrement();
    }

    @Override
    public Long getResult() {
        return quantity.get();
    }

    @Override
    public String getName() {
        return null;
    }
}
