package fr.univ_amu;

import java.io.*;

public class Benchmark {
    private long start;
    private long end;
    private long elapsedTime;
    private PrintWriter write;

    public void timeFibonacciIteratif(int n) throws IOException {
        FileWriter file = new FileWriter("test.txt");
        write = new PrintWriter(file);
        start = System.nanoTime();
        Fibonacci.calculFibonacciIteratif(n);
        end = System.nanoTime();
        elapsedTime = end - start;
        System.out.println("Temps d'execution en nanosecondes: "+ elapsedTime);
        write.println(elapsedTime);
        write.close();
    }

    public void timeFibonacciRecursif(int n){
        start = System.currentTimeMillis();
        Fibonacci.calculFibonacciRecursive(n);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println("Temps d'execution en millisecondes: "+ elapsedTime);
    }

    public void timeFibonacciMatrix(int n){
        start = System.currentTimeMillis();
        Fibonacci.calculFibonacciMatrice(n);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println("Temps d'execution en millisecondes: "+ elapsedTime);
    }

}
