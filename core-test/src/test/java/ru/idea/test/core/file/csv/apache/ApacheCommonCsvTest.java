package ru.idea.test.core.file.csv.apache;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import ru.idea.test.core.file.csv.GenerateRandomCsv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static ru.idea.test.core.file.csv.GenerateRandomCsv.CSV_LINE_DELIMITER;

public class ApacheCommonCsvTest {

    enum InHeader {
        FIRST, SECOND
    }

    enum OutHeader {
        FIRST, SECOND
    }

    public static final char OUT_CSV_LINE_DELIMITER = '|';
    public static final String CSV_RESOURCES_OUTPUT_PATH = "D:\\IdeaProjects\\idea-test\\core-test\\src\\test\\resources\\csv\\output";
    public static final String OUTPUT_CSV_FILE = CSV_RESOURCES_OUTPUT_PATH + "\\APACHE_COMMON_CSV.csv";

    @Test
    public void shouldRead() throws IOException {
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(InHeader.class)
                .withDelimiter(CSV_LINE_DELIMITER)
//                .withFirstRecordAsHeader()
                .parse(new FileReader(GenerateRandomCsv.FILE_PATH));
        for (CSVRecord record : records) {
            String f = record.get(InHeader.FIRST);
            String s = record.get(InHeader.SECOND);
            System.out.println(f + " --- " + s);
        }
    }

    @Test
    public void shouldWrite() throws IOException {
        try (CSVPrinter printer = new CSVPrinter(
                new FileWriter(OUTPUT_CSV_FILE),
                CSVFormat.DEFAULT.withDelimiter(OUT_CSV_LINE_DELIMITER).withSkipHeaderRecord().withHeader(OutHeader.class))
        ) {

            printer.printRecord("aaa","ccc");
        }
    }
}
