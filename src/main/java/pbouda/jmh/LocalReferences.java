package pbouda.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class LocalReferences {

    private Object referencedObject = new Object();

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LocalReferences.class.getSimpleName())
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void globalReference(Blackhole bh) {
        bh.consume(referencedObject);
        bh.consume(referencedObject);
        bh.consume(referencedObject);
        bh.consume(referencedObject);
    }

    @Benchmark
    public void localReference(Blackhole bh) {
        Object localReference = referencedObject;

        bh.consume(localReference);
        bh.consume(localReference);
        bh.consume(localReference);
        bh.consume(localReference);
    }
}
