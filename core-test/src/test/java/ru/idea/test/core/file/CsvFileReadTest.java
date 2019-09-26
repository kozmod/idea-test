package ru.idea.test.core.file;

import org.junit.Assert;
import org.junit.Test;
import ru.idea.test.core.TestUtils;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

public class CsvFileReadTest {

    static final String DELIMITER = GenerateRandomCsv.CSV_LINE_DELIMITER;

    @Test
    public void shouldRead() {
        FileLineIndexContext<Long> flpc = FileLineIndexContext.of(firstCsvLineValueLongKeyExtractor(), new HashMap<>());
        FileLineProcessor p = new FileLineProcessor(flpc);
        long et = TestUtils.executionTime(() -> p.process(GenerateRandomCsv.FILE_PATH));

        flpc.getLinesContainsKey().forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
        System.out.println(et);
    }

    static Function<String, Optional<Long>> firstCsvLineValueLongKeyExtractor() {
        return s -> {
            try {
                return Optional.of(
                        Long.valueOf(s.split(DELIMITER)[0])
                );
            } catch (NumberFormatException ex) {
                return Optional.empty();
            }
        };
    }
}
