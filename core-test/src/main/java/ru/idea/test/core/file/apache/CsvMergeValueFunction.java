package ru.idea.test.core.file.apache;

import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;

public class CsvMergeValueFunction implements CsvLineTask<ConvertContext> {
    private static final String DELIMITER = ",";

    private final Map<OutHeader, Set<String>> outLineMap = new EnumMap<>(OutHeader.class);

    private Function<CSVRecord, String> keyExtractor = record -> record.get(InHeader.FIRST);
    private Function<CSVRecord, Collection<String>> valueExtractor = record -> Arrays.asList(record.get(InHeader.SECOND).split(DELIMITER));
    private String currentKey;

    @Override
    public ConvertContext apply(CSVRecord record) {
        String key = keyExtractor.apply(record);
        if (currentKey == null) {
            currentKey = key;
            initFirst(record);
            return ConvertContext.nonReady();
        }
        if (Objects.equals(currentKey, key)) {
            merge(OutHeader.SECOND, valueExtractor.apply(record));
            return ConvertContext.nonReady();
        } else {
            String[] mergeString = prepareValue();
            currentKey = key;
            initFirst(record);
            return ConvertContext.ready(mergeString);
        }
    }

    @Override
    public ConvertContext result() {
        return ConvertContext.nonReady(prepareValue());
    }

    private void initFirst(CSVRecord record) {
        outLineMap.put(OutHeader.FIRST, newSet(record.get(InHeader.FIRST)));
        outLineMap.put(OutHeader.SECOND, newSet(record.get(InHeader.SECOND)));
    }

    private Set<String> newSet(String value) {
        Set<String> set = new HashSet<>();
        set.add(value);
        return set;
    }

    private void merge(OutHeader header, Collection<String> addValues) {
        Set<String> exValues = outLineMap.get(header);
        exValues.addAll(addValues);
    }

    private String[] prepareValue() {
        Collection<Set<String>> values = outLineMap.values();
        String[] result = new String[values.size()];
        int i = 0;
        for (Set<String> value : values) {
            result[i++] = join(value, DELIMITER);
        }
        return result;
    }

    private String join(Collection<String> strings, String delimiter) {
        StringJoiner sj = new StringJoiner(delimiter);
        for (String value : strings) {
            sj.add(value);
        }
        return sj.toString();
    }

}
