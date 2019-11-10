package ru.idea.test.jmh;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.idea.test.jmh.benchmark.ListAddFirstBenchmark;
import ru.idea.test.jmh.profiler.MemoryProfiler;
import ru.idea.test.jmh.utils.WarningUtils;

public class ListAddBenchmarkTest {

    @BeforeClass
    public static void init(){
        WarningUtils.disable();
    }

    @Test
    public void addFirst_List() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ListAddFirstBenchmark.class.getSimpleName())
                .warmupForks(1)
                .warmupIterations(1)
                .measurementIterations(1)
                .measurementIterations(1)
                .addProfiler(MemoryProfiler.class)
                .build();
        new Runner(opt).run();
    }
}
