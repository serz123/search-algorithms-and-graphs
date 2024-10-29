package com.example.timer;

import java.util.ArrayList;
import java.util.List;

public class Timer {

    public long timeit(Runnable satetment, Runnable setup, int iterations) {

        setup.run();

        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            satetment.run();
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / iterations;
    }

    public List<Long> repeat(Runnable satetment, Runnable setup, int iterations, int repeats) {
        List<Long> results = new ArrayList<>();
        for (int i = 0; i < repeats; i++) {
            results.add(timeit(satetment, setup, iterations));
        }
        return results;
    }

    public long timeit(Runnable statement, Runnable setup) {
        return timeit(statement, setup, 1); // Default iterations is 1
    }

    public long timeit(Runnable statement) {
        return timeit(statement, () -> {
        }, 1); // Default setup is a no-op Runnable and iterations is 1
    }

    public long timeit() {
        return timeit(() -> {
        }, () -> {
        }, 1); // All defaults: no-op Runnables and 1 iteration
    }
}
