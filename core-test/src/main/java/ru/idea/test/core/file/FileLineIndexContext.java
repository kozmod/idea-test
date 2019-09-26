package ru.idea.test.core.file;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public final class FileLineIndexContext<K> implements BiConsumer<Long, String> {

    private final Function<String, Optional<K>> keyExtractor;
    private final Map<K, Set<Long>> linesContainsKey;

    public FileLineIndexContext(Function<String, Optional<K>> keyExtractor, Map<K, Set<Long>> linesContainsKey) {
        this.keyExtractor = keyExtractor;
        this.linesContainsKey = linesContainsKey;
    }

    public static <K> FileLineIndexContext<K> of(Function<String, Optional<K>> keyExtractor, Map<K, Set<Long>> linesContainsKey) {
        return new FileLineIndexContext<>(keyExtractor, linesContainsKey);
    }

    @Override
    public void accept(Long lineNumber, String string) {
        if (lineNumber != null && string != null) {
            keyExtractor.apply(string)
                    .ifPresent(key -> {
                                Set<Long> linesByKey = linesContainsKey.computeIfAbsent(key, k -> new HashSet<>());
                                linesByKey.add(lineNumber);
                            }
                    );
        }
    }

    public Map<K, Set<Long>> getLinesContainsKey() {
        return linesContainsKey;
    }
}
