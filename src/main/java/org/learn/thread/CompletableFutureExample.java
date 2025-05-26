package org.learn.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. runAsync(Runnable r, Executor e)
        CompletableFuture<Void> futureRunnable = CompletableFuture.runAsync(()-> System.out.println("run"));

        // 2. supplyAsync(Supplier r, Executor e)
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        // 3. thenApply(): Applies a function to the result of the future.
        CompletableFuture<String> newFuture = future.thenApply(s -> s + ", World!");

        // 4. thenAccept(): Consumes the result of the future and performs an action.
        newFuture.thenAccept(System.out::println); // Hello, World!

        // 5. thenRun(): Performs an action after the future completes.
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello");
        future2.thenRun(() -> System.out.println("Future completed"));

        // 6. thenCombine(): Combines the results of two futures.
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> "World");
        CompletableFuture<String> newFuture5 = future4.thenCombine(future3, (s1, s2) -> s1 + ", " + s2);
        newFuture5.thenAccept(System.out::println); // Hello, World!

        // 7. thenAcceptBoth(): Consumes the results of two futures and performs an action.
        CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future72 = CompletableFuture.supplyAsync(() -> "World");
        CompletableFuture<Void> newFuture7 = future7.thenAcceptBoth(future72,
                (s1, s2) -> System.out.println(s1 + ", " + s2));

        // 8. exceptionally(): Handles exceptions thrown by the future.
        CompletableFuture<String> future8 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Something went wrong");
        });
        CompletableFuture<String> newFuture8 = future8.exceptionally(ex -> "Error: " + ex.getMessage());

        // 9. get(): Blocks until the future completes and returns the result.
        CompletableFuture<String> future9 = CompletableFuture.supplyAsync(() -> "Hello");
        String result = future9.get(); // Blocks until the future completes

        // 10. join(): Blocks until the future completes and returns the result (similar to get()).
        CompletableFuture<String> future10 = CompletableFuture.supplyAsync(() -> "Hello");
        String result10 = future10.join(); // Blocks until the future completes
    }
}
