package fr.univ_amu;

public class Fibonacci {
    public static long calculFibonacciRecursive(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else {
            return calculFibonacciRecursive(n - 1) + calculFibonacciRecursive(n - 2);
        }
    }

    public static long calculFibonacciIteratif(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else {
            long a = 0;
            long b = 1;
            long temp = 0;
            for (int i = 0; i < n - 1; i++) {
                temp = a + b;
                a = b;
                b = temp;
            }
            return b;
        }
    }

    public static long calculFibonacciMatrice(int n) {
        long[][] fibonacci = {{0, 1}, {1, 1}};
        fibonacci = Math.powerMatrix(fibonacci, n);
        return fibonacci[0][1];
    }
}
