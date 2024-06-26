package org.learn.algorithms;

import java.math.BigInteger;

public class PrimeCheck {
    public static void main(String[] args) {
        BigInteger number = new BigInteger("4");
        // Record the start time
        long startTime = System.nanoTime();
        
        // Check if the number is probably prime with high certainty (e.g., 10)
        boolean isPrime = number.isProbablePrime(10);
        
        // Record the end time
        long endTime = System.nanoTime();
        
        // Calculate the elapsed time in milliseconds
        long duration = (endTime - startTime) / 1_000_000;
        
        // Print whether the number is prime or not
        if (isPrime) {
            System.out.println("prime");
        } else {
            System.out.println("not prime");
        }
        
        // Print the time taken to perform the check
        System.out.println("Time taken: " + duration + " milliseconds");
    }
}
