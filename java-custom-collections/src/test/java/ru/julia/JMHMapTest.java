package ru.julia;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.openjdk.jmh.annotations.Mode.SingleShotTime;

import java.util.HashMap;
import java.util.Map;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@BenchmarkMode(SingleShotTime)
@OutputTimeUnit(MILLISECONDS)
public class JMHMapTest {
    private final int mapSize = 200_000;
    private final String keyStr = "key";
    private CustomMapInt customMapInt;
    private Map<String, Integer> hashMap;

    public static void main(String[] args) throws RunnerException {
        var opt = new OptionsBuilder()
                .include(JMHMapTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        customMapInt = new CustomMapInt(mapSize * 2);
        hashMap = new HashMap<>(mapSize);
    }

    @Benchmark
    public long CustomMapIntTest() {
        for (int i = 0; i < mapSize; i++) {
            customMapInt.put(keyStr + i, i);
        }

        int sum = 0;
        for (int i = 0; i < mapSize; i++) {
            sum += customMapInt.get(keyStr + i);
        }

        return sum;
    }

    @Benchmark
    public long hashMapTest() {
        for (int i = 0; i < mapSize; i++) {
            hashMap.put(keyStr + i, i);
        }

        int sum = 0;
        for (int i = 0; i < mapSize; i++) {
            sum += hashMap.get(keyStr + i);
        }

        return sum;
    }
}