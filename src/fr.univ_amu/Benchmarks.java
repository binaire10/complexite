package fr.univ_amu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Benchmarks {
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
        long[] result = new long[1000];
        FileWriter resultFile = new FileWriter(args[0]);
        resultFile.write(IntStream.rangeClosed(1, result.length).mapToObj(Integer::toString).reduce((a, b) -> a + ';' + b).get());
        resultFile.write('\n');
        for (int i = 0; i < Integer.min(result.length, 40); i++)
            result[i] = benchmark(i + 1, 100, Fibonacci::calculFibonachiRecursive);

        resultFile.write(LongStream.of(result).limit(Integer.min(result.length, 40)).mapToObj(Long::toString).reduce((a, b) -> a + ';' + b).get());
        resultFile.write('\n');

        for (int i = 0; i < result.length; i++)
            result[i] = benchmark(i + 1, 800, Fibonacci::calculFibonacciIteratif);

        resultFile.write(LongStream.of(result).mapToObj(Long::toString).reduce((a, b) -> a + ';' + b).get());
        resultFile.write('\n');

        for (int i = 0; i < result.length; i++)
            result[i] = benchmark(i + 1, 800, Fibonacci::calculFibonacciMatrice);

        resultFile.write(LongStream.of(result).mapToObj(Long::toString).reduce((a, b) -> a + ';' + b).get());
        resultFile.write('\n');
        resultFile.close();
    }
}
