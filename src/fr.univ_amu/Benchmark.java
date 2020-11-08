package fr.univ_amu;

import java.io.*;
import java.util.function.*;
import java.util.stream.*;

public class Benchmark {
    private static long benchmark(int value, int repeat, IntConsumer consumer) {
        long best = Integer.MAX_VALUE;
        for (int i = 0; i < repeat; i++) {
            long start = System.nanoTime();
            consumer.accept(value);
            long end = System.nanoTime();
            best = Long.min(best, end - start);
        }
        return best;
    }
    public static void main(String[] args) throws IOException {
        long[] result = new long[92];
        FileWriter resultFile = new FileWriter("result.csv");
        resultFile.write(IntStream.rangeClosed(1, result.length).mapToObj(Integer::toString).reduce((a, b) -> a + ';' + b).get());
        resultFile.write('\n');
        for (int i = 0; i < Integer.min(result.length, 11); i++)
            result[i] = benchmark(i+1, 10, Fibonacci::calculFibonacciRecursive);

        resultFile.write(LongStream.of(result).limit(Integer.min(result.length, 11)).mapToObj(Long::toString).reduce((a, b) -> a + ';' + b).get());
        resultFile.write('\n');

        for (int i = 0; i < result.length; i++)
            result[i] = benchmark(i+1, 10_000, Fibonacci::calculFibonacciIteratif);

        resultFile.write(LongStream.of(result).mapToObj(Long::toString).reduce((a, b) -> a + ';' + b).get());
        resultFile.write('\n');

        for (int i = 0; i < result.length; i++)
            result[i] = benchmark(i+1, 100_000, Fibonacci::calculFibonacciMatrice);

        resultFile.write(LongStream.of(result).mapToObj(Long::toString).reduce((a, b) -> a + ';' + b).get());
        resultFile.write('\n');
        resultFile.close();
    }
}