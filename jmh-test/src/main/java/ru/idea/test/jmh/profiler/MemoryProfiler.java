package ru.idea.test.jmh.profiler;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;
import org.openjdk.jmh.profile.InternalProfiler;
import org.openjdk.jmh.results.AggregationPolicy;
import org.openjdk.jmh.results.IterationResult;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.ScalarResult;

import java.util.Collection;
import java.util.List;

public class MemoryProfiler implements InternalProfiler {

    @Override
    public String getDescription() {
        return "Memory heap profiler";
    }

    @Override
    public void beforeIteration(BenchmarkParams benchmarkParams, IterationParams iterationParams) {
    }

    @Override
    public Collection<? extends Result> afterIteration(
            BenchmarkParams benchmarkParams,
            IterationParams iterationParams,
            IterationResult result
    ) {
        return List.of(
                new ScalarResult("Total memory heap", Runtime.getRuntime().totalMemory(), "bytes", AggregationPolicy.MAX),
                new ScalarResult("Free memory heap", Runtime.getRuntime().freeMemory(), "bytes", AggregationPolicy.MAX),
                new ScalarResult("Max memory heap", Runtime.getRuntime().maxMemory(), "bytes", AggregationPolicy.MAX)
        );
    }
}