package ru.idea.test.core.file.csv.sort;

import com.github.davidmoten.bigsorter.Sorter;
import org.junit.Test;
import ru.idea.test.core.TestUtils;
import ru.idea.test.core.file.csv.GenerateRandomCsv;

import java.io.File;

public class CsvFileSort {

    private static final char DELIMITER = GenerateRandomCsv.CSV_LINE_DELIMITER;
    public static final String CSV_RESOURCES_OUTPUT_PATH = "D:\\IdeaProjects\\idea-test\\core-test\\src\\test\\resources\\csv\\output";
    public static final String SORTED_OUTPUT_FILE = CSV_RESOURCES_OUTPUT_PATH + "\\SORTED_CSV_OUTPUT.csv";

    @Test
    public void shouldSort() {
        long exMillis = TestUtils.executionTime(() ->
                Sorter.serializerLinesUtf8()
                        .comparator((a, b) -> {
                            long l1 = Long.parseLong(a.split(String.valueOf(DELIMITER))[0]);
                            long l2 = Long.parseLong(b.split(String.valueOf(DELIMITER))[0]);
                            return Long.compare(l1, l2);
                        })
                        .input(new File(GenerateRandomCsv.FILE_PATH))
                        .output(new File(SORTED_OUTPUT_FILE))
                        .sort()
        );
        System.err.println(exMillis);
    }
}
