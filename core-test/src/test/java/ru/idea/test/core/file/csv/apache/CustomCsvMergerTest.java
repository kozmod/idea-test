package ru.idea.test.core.file.csv.apache;

import com.github.davidmoten.bigsorter.Sorter;
import org.apache.commons.csv.CSVFormat;
import org.junit.Test;
import ru.idea.test.core.TestUtils;
import ru.idea.test.core.file.apache.CsvConverter;
import ru.idea.test.core.file.apache.CsvMergeValueFunction;
import ru.idea.test.core.file.apache.InHeader;
import ru.idea.test.core.file.apache.OutHeader;
import ru.idea.test.core.file.csv.GenerateRandomCsv;

import java.io.File;

import static ru.idea.test.core.file.csv.sort.CsvFileSort.CSV_RESOURCES_OUTPUT_PATH;
import static ru.idea.test.core.file.csv.sort.CsvFileSort.SORTED_OUTPUT_FILE;

public class CustomCsvMergerTest {

    private static final char DELIMITER = GenerateRandomCsv.CSV_LINE_DELIMITER;
    public static final String SORTED_MERGE_OUTPUT_FILE = CSV_RESOURCES_OUTPUT_PATH + "\\SORTED_MERGE_OUT_CSV.csv";

    @Test
    public void shouldSortAndMerge() {
        long lineQuantity = TestUtils.fileLineQuantity(new File(GenerateRandomCsv.FILE_PATH));
        System.err.println("Line quantity: " + lineQuantity);

        long sortMillis = TestUtils.executionTime(() ->
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
        System.err.println("sort: " + sortMillis);

        CsvConverter converter = new CsvConverter(
                CSVFormat.DEFAULT.withDelimiter(DELIMITER).withHeader(InHeader.class),
                CSVFormat.DEFAULT.withDelimiter(DELIMITER).withSkipHeaderRecord().withHeader(OutHeader.class),
                CsvMergeValueFunction::new
        );
        long mergeMillis = TestUtils.executionTime(() -> converter.convert(SORTED_OUTPUT_FILE, SORTED_MERGE_OUTPUT_FILE));
        System.err.println("merge: " + mergeMillis);
    }
}
