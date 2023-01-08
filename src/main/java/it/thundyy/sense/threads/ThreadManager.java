package it.thundyy.sense.threads;

import org.apache.commons.net.nntp.Threader;

import java.util.concurrent.*;

public class ThreadManager {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(3);
    
    public static void runAsync(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }
    
    public static <T> Future<T> callAsync(Callable<T> callable) {
        return EXECUTOR.submit(callable);
    }
    
    public static void shutdown() {
        EXECUTOR.shutdown();
    }
}
