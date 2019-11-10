package ru.idea.test.jmh.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
//@Warmup(iterations = 3)
//@Measurement(iterations = 3)
public class ListAddFirstBenchmark {

    @Param({"100"})
    private int ADD_ELEMENTS_IN_FIRST_POSITION;

    @Param({"10"})
    private int RANDOM_STRING_LENGTH;

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<String> ARRAY_LIST;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<String> LINKED_LIST;

    @Setup
    public void setup() {
        ARRAY_LIST = new ArrayList<>();
        LINKED_LIST = new LinkedList<>();
    }

    @Benchmark
    public void addFirst_ArrayList() {
        for (int i = 0; i < ADD_ELEMENTS_IN_FIRST_POSITION; i++) {
           ARRAY_LIST.add(0, randomString(RANDOM_STRING_LENGTH));
        }
    }

    @Benchmark
    public void addFirst_LinkedList() {
        for (int i = 0; i < ADD_ELEMENTS_IN_FIRST_POSITION; i++) {
            LINKED_LIST.add(0, randomString(RANDOM_STRING_LENGTH));
        }
    }


    private static String randomString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
