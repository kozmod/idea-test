package ru.idea.test.core.file.apache;

import org.apache.commons.csv.CSVRecord;

import java.util.function.Function;

public interface CsvLineTask<R> {

    R apply(CSVRecord record);

    R result();
}
