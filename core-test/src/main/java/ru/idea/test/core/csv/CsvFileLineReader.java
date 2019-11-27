package ru.idea.test.core.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.idea.test.core.csv.task.StatisticTask;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class CsvFileLineReader<T> {

    private static final String filePath = "D:\\IdeaProjects\\idea-test\\core-test\\src\\main\\resources\\test.csv";

    private final CheckedFunction<File, Iterable<T>> recordSupplier;
    private final List<StatisticTask<T, ?>> tasks;;

    public CsvFileLineReader(CheckedFunction<File, Iterable<T>> recordSupplier, List<StatisticTask<T, ?>> tasks) {
        this.recordSupplier = recordSupplier;
        this.tasks = tasks;
    }

    void process(File file) throws Exception {
        for (T record : recordSupplier.apply(file)) {
            for (StatisticTask<T, ?>task : tasks){
                task.apply(record);
            }
        }
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        CustomerKnowledgeStatistic s = new CustomerKnowledgeStatistic();
        CsvFileLineReader<CSVRecord> processor = new CsvFileLineReader<>(
                f -> CSVFormat.newFormat(';').withHeader(CsvHeader.class).parse(new FileReader(f))
                , s.getAllTasks()
        );
        processor.process(new File(filePath));

        System.out.println();
    }
}
