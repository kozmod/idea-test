package ru.idea.test.csv;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static ru.idea.test.csv.FastGenCsv.name;
import static ru.idea.test.csv.FastGenCsv.pathLng;

public class ForkJoinReadCsvTest {
    static class CustomRecursiveAction extends RecursiveAction {

        private long start;
        private long threshold;
        private String path;
        private int tasks;
        private Consumer<String> action;

        private CustomRecursiveAction(long start, long threshold, String path, int tasks, Consumer<String> action) {
            this.start = start;
            this.threshold = start + threshold;
            this.path = path;
            this.tasks = tasks;
            this.action = action;
        }

        public CustomRecursiveAction(long threshold, String path, int tasks, Consumer<String> action) {
            this.start = 0;
            this.threshold = start + threshold;
            this.path = path;
            this.tasks = tasks;
            this.action = action;
        }

        @Override
        protected void compute() {
            if (tasks > 0) {
                ForkJoinTask.invokeAll(createSubtasks());
            } else {
                processing(path);
            }
        }

        private List<CustomRecursiveAction> createSubtasks() {
            List<CustomRecursiveAction> subtasks = new ArrayList<>();
            for (int i = 0; i < tasks; i++) {
                subtasks.add(new CustomRecursiveAction(
                        i * threshold,
                        threshold,
                        path,
                        0,
                        action
                ));
            }
            return subtasks;
        }

        private void processing(String file) {
            int lineNum = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lineNum++;
                    if (lineNum < start) {
                        continue;
                    }
                    if (lineNum >= threshold) {
                        break;
                    }
                    action.accept(line);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Test
    public void shouldAddToMap() throws InterruptedException {
        ForkJoinPool fjp = new ForkJoinPool(5);
        fjp.execute(
                new CustomRecursiveAction(
                        100,
                        pathLng.concat(name),
                        5,
                        System.out::println
                )
        );
        fjp.awaitTermination(10,TimeUnit.SECONDS); //todo need finish
    }
}
