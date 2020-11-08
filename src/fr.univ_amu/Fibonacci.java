package fr.univ_amu;

import java.math.BigInteger;

public class Fibonacci {
    public static BigInteger calculFibonachiRecursive(int n) {
        if (n == 0) return BigInteger.ZERO;
        else if (n == 1) return BigInteger.ONE;
        else {
            return calculFibonachiRecursive(n - 1).add(calculFibonachiRecursive(n - 2));
        }
    }

    public static BigInteger calculFibonacciIteratif(int n) {
        if (n == 0) return BigInteger.ZERO;
        else if (n == 1) return BigInteger.ONE;
        else {
            BigInteger a = BigInteger.ZERO;
            BigInteger b = BigInteger.ONE;
            for (int i = 0; i < n - 1; i++) {
                BigInteger temp = a.add(b);
                a = b;
                b = temp;
            }
            return b;
        }
    }

    public static BigInteger calculFibonacciMatrice(int n) {
        BigInteger[][] fibonacci = {{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
        fibonacci = Math.powerMatrix(fibonacci, n);
        return fibonacci[0][1];
    }
}
