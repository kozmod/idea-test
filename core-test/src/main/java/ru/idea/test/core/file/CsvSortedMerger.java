package ru.idea.test.core.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public final class CsvSortedMerger<K> {

    private Function<String,K> keyExtractor;
    private Function<String,K> mergeValueExtractor;

    public void merge(File in, File out){
        try (BufferedReader br = new BufferedReader(new FileReader(in))) {
            String line;
            while ((line = br.readLine()) != null) {

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
