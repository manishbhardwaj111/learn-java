package org.learn.thread;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Singleton {
    private static Singleton instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private Singleton(){

    }

    public static Singleton getInstance() {
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static Singleton getInstance1() {
        if (instance == null){
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Singleton();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(index->{
            System.out.println(getInstance());
            System.out.println(getInstance1());
        });
    }
}
