package ru.idea.test.core.file.csv;

import ru.idea.test.core.TestUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandomCsv {

    public static final char CSV_LINE_DELIMITER = ';';

    static final String FILE_NAME = "\\Random_CSV_Lines.csv";
    public static final String CSV_RESOURCES_INPUT_PATH = "D:\\IdeaProjects\\idea-test\\core-test\\src\\test\\resources\\csv\\input";
    public static final String FILE_PATH = CSV_RESOURCES_INPUT_PATH + FILE_NAME;

    static final long LINE_QUANTITY = 100_000_000;

    public static void main(String[] args) {
        long exMillis = TestUtils.executionTime(GenerateRandomCsv::generateRandomCsv);
        System.err.println(exMillis);
    }

    static void generateRandomCsv() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File(FILE_PATH)))) {
            for (int i = 1; i <= LINE_QUANTITY; i++) {
                String line = randomCsvLine();
                br.write(line);
                br.newLine();
            }
            br.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String randomCsvLine() {
        String id = Long.toString(ThreadLocalRandom.current().nextLong(1, 1000000));
        String secondColumn = randomString();
        String thirdColumn = randomString();
        return id
                + CSV_LINE_DELIMITER
                + secondColumn
                + CSV_LINE_DELIMITER
                + thirdColumn;

    }

    static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
