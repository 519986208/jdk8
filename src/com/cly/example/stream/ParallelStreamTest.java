package com.cly.example.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ParallelStreamTest {

    //并行Streams比串行速度快
    @Test
    public void parallelStream() {
        int max = 200_0000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        values.stream().sorted().count();
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

        long t3 = System.nanoTime();
        values.parallelStream().sorted().count();
        long t4 = System.nanoTime();
        long millis2 = TimeUnit.NANOSECONDS.toMillis(t4 - t3);
        System.out.println(String.format("parallel sort took: %d ms", millis2));
    }

}
