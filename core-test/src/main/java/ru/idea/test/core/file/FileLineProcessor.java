package ru.idea.test.core.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BiConsumer;

public final class FileLineProcessor {

    private final BiConsumer<Long, String> lineNumberValueConsumer;

    public FileLineProcessor(BiConsumer<Long, String> lineNumberValueConsumer) {
        this.lineNumberValueConsumer = lineNumberValueConsumer;
    }

    public long process(String file) {
        return process(new File(file));
    }

    public long process(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            long lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumberValueConsumer.accept(++lineNumber, line);
            }
            return lineNumber;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
