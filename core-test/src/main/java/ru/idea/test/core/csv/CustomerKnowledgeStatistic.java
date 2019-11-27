package ru.idea.test.core.csv;

import org.apache.commons.csv.CSVRecord;
import ru.idea.test.core.csv.CsvHeader;
import ru.idea.test.core.csv.task.CountAllLineTask;
import ru.idea.test.core.csv.task.CountUniqueId;
import ru.idea.test.core.csv.task.CountUniqueVal;
import ru.idea.test.core.csv.task.StatisticTask;

import java.util.List;
import java.util.Map;

public class CustomerKnowledgeStatistic {

    private StatisticTask<CSVRecord, Long> countAllLineTask = new CountAllLineTask();
    private StatisticTask<CSVRecord, Integer> countAllUniqueId = new CountUniqueId<>(CsvHeader.ID);
    private StatisticTask<CSVRecord, Map<String, Integer>> countUniqueMarkers = new CountUniqueVal<>(CsvHeader.VAL);

    public List<StatisticTask<CSVRecord,?>> getAllTasks(){
        return List.of(
                countAllLineTask,
                countAllUniqueId,
                countUniqueMarkers
        );
    }
}
