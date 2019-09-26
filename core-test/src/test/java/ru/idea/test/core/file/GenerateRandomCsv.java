package ru.idea.test.core.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandomCsv {

    static final String CSV_LINE_DELIMITER = ";";

    static final long LINE_QUANTITY = 10_000_000;
    static final String FILE_NAME = "\\Random_CSV_Lines.csv";
    static final String FILE_PATH = "D:\\IdeaProjects\\idea-test\\core-test\\src\\test\\resources" + FILE_NAME;

    public static void main(String[] args) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File(FILE_PATH)))) {
            for (int i = 1; i <= LINE_QUANTITY; i++) {
                br.write(randomCsvLine());
                br.newLine();
            }
            br.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String randomCsvLine() {
        return ThreadLocalRandom.current().nextLong(1,10)
                + CSV_LINE_DELIMITER
                + randomString()
                + CSV_LINE_DELIMITER
                + randomString();

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
