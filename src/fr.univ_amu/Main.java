package fr.univ_amu;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{

public static void main(String[] args) throws IOException {

    int n = 1000;
    //System.out.println(Fibonacci.calculFibonacciIteratif(n));
    //System.out.println(Fibonacci.calculFibonacciRecursive(n));
    //System.out.println(Fibonacci.calculFibonacciMatrice(n));
    Benchmark benchmark = new Benchmark();
    benchmark.timeFibonacciIteratif(n);
    //benchmark.timeFibonacciRecursif(n);
    benchmark.timeFibonacciMatrix(n);



 }
}
