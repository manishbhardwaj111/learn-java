package org.learn.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    public static void main(String[] args) throws InterruptedException {
        try (ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2)) {
            Runnable task = () -> System.out.println("Task executed at " + getCurrentTime());
            executorService.scheduleAtFixedRate(task, 3, 5, TimeUnit.SECONDS);
            Thread.currentThread().join();
        }
    }

    public static String getCurrentTime() {
        System.out.println(Thread.currentThread().getName() + " = Started");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Date date = new Date(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() + " = Completed");
        return simpleDateFormat.format(date);
    }
}
