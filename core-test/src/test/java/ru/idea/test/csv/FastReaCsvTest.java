package ru.idea.test.csv;

import org.junit.Test;
import ru.idea.test.utils.TestStringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class FastReaCsvTest {

    private static final String pathLng = "/Users/a18388871/IdeaProjects/idea-test/core-test/src/test/resources/csv/gen/";

    @Test
    public void genCsv() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathLng.concat("lng.csv")))) {
            for (int i = 0; i < 100_000; i++) {
                bw.write(new RandomCsvLine().toString());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static class RandomCsvLine {
        int id;
        String val_1;
        String val_2;
        String val_3;
        String val_4;
        String val_5;
        String delimiter = ";";

        public RandomCsvLine() {
            this.id = ThreadLocalRandom.current().nextInt();
            this.val_1 = TestStringUtils.randomAlphabeticString(1);
            this.val_2 = TestStringUtils.randomAlphabeticString(2);
            this.val_3 = TestStringUtils.randomAlphabeticString(3);
            this.val_4 = TestStringUtils.randomAlphabeticString(4);
            this.val_5 = TestStringUtils.randomAlphabeticString(5);
        }

        @Override
        public String toString() {
            return id + delimiter +
                            val_1 + delimiter +
                            val_2 + delimiter +
                            val_3 + delimiter +
                            val_4 + delimiter +
                            val_5;
        }
    }
}
