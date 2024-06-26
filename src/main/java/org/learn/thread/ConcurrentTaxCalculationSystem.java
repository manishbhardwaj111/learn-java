package org.learn.thread;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurrentTaxCalculationSystem {
    
    public record Product(int id, BigDecimal price, BigDecimal tax, BigDecimal totalPrice) {
        public Product(int id, BigDecimal price) {
            this(id, price, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        public Product withTaxAndTotalPrice(BigDecimal tax, BigDecimal totalPrice) {
            return new Product(this.id, this.price, tax, totalPrice);
        }
    }

    public static class TaxProvider {
        private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.20);

        public static BigDecimal calculateTax(BigDecimal price) {
            try {
                TimeUnit.MILLISECONDS.sleep(200); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return price.multiply(TAX_RATE);
        }
    }
    @Data
    public static class TaxInvoiceService {
        private final ExecutorService executorService;
        private final Map<Integer, Product> productMap = new ConcurrentHashMap<>();

        public TaxInvoiceService(int numberOfThreads) {
            this.executorService = Executors.newFixedThreadPool(numberOfThreads);
        }

        public void calculateTaxForProducts(List<Product> products) {
            List<Callable<Product>> tasks = products.stream()
                    .map(product -> (Callable<Product>) () -> {
                        BigDecimal tax = TaxProvider.calculateTax(product.price());
                        BigDecimal totalPrice = product.price().add(tax);
                        return product.withTaxAndTotalPrice(tax, totalPrice);
                    }).toList();

            try {
                List<Future<Product>> futures = executorService.invokeAll(tasks);
                for (Future<Product> future : futures) {
                    Product product = future.get();
                    productMap.put(product.id(), product);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        public void shutdown() {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        public BigDecimal getTotalTax() {
            return productMap.values().stream()
                    .map(Product::tax)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int price = (int) (Math.random() * 1000);
            products.add(new Product(i, BigDecimal.valueOf(price)));
        }

        TaxInvoiceService taxInvoiceService =
                new TaxInvoiceService(Runtime.getRuntime().availableProcessors()*2);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            Instant start = Instant.now();
            taxInvoiceService.calculateTaxForProducts(products);
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);

            BigDecimal totalTax = taxInvoiceService.getTotalTax();
            System.out.println("Total tax computed: " + totalTax);
            System.out.println("Time taken to compute tax: " + duration.toMillis() + " ms");
        };

        scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
            taxInvoiceService.shutdown();
        }));
    }
}
