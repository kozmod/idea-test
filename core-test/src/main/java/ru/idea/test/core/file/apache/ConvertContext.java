package ru.idea.test.core.file.apache;

import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;

public class ConvertContext {

    private final String[] csvLine;
    private final boolean ready;

    private ConvertContext() {
        this.csvLine = null;
        this.ready = false;
    }

    private ConvertContext(String[] csvLine, boolean ready) {
        this.csvLine = csvLine;
        this.ready = ready;
    }

    public Optional<String[]> getCsvLine() {
        return Optional.ofNullable(csvLine);
    }

    public boolean isReady() {
        return csvLine != null && ready;
    }

    public static ConvertContext nonReady() {
        return new ConvertContext();
    }

    public static ConvertContext nonReady(String[] csvLine) {
        return new ConvertContext(csvLine, false);
    }

    public static ConvertContext ready(String[] lineValue) {
        return new ConvertContext(lineValue, true);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConvertContext.class.getSimpleName() + "[", "]")
                .add("csvLine=" + Arrays.toString(csvLine))
                .add("ready=" + ready)
                .toString();
    }
}
