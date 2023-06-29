package org.example;

import java.util.TooManyListenersException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        int n = 43;

        long start = System.nanoTime();

        int fibN = fib(n);

        System.out.printf("result = %d is: %d \n", n, fibN);

        long elapsedTime = System.nanoTime() - start;

        // outputs in different formats: ms, ns, seconds, minutes

        double seconds = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        double secondDecimalPrecision = (double) elapsedTime / 1000000000.0;
        double ms = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        double minutes = TimeUnit.MINUTES.convert(elapsedTime, TimeUnit.NANOSECONDS);

        System.out.printf("Nanoseconds: %d ns \n", elapsedTime);
        System.out.printf("Milliseconds: %f ms \n", ms);
        System.out.printf("Seconds %f s \n", seconds);
        System.out.printf("Seconds2 %f s \n", secondDecimalPrecision);
        System.out.printf("Minutes %f", minutes);
    }

    static int fib(int n) {
        if (n < 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}