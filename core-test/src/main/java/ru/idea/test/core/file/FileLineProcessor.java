package ru.idea.test.core.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public final class FileLineProcessor {

    private final Function<String,?> lineConsumer;

    public FileLineProcessor(Function<String,?> lineNumberValueConsumer) {
        this.lineConsumer = lineNumberValueConsumer;
    }

    public long process(String file) {
        return process(new File(file));
    }

    public long process(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            long lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineConsumer.apply(line);
            }
            return lineNumber;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
