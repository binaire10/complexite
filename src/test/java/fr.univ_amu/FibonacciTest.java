package fr.univ_amu;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciTest {
    @Test
    public void test_fibo() {
        for (int i = 0; i < 500; i++) {
            BigInteger a = Fibonacci.calculFibonacciIteratif(i);
            BigInteger b = Fibonacci.calculFibonacciMatrice(i);
            assertEquals(a, b);
        }
    }
}