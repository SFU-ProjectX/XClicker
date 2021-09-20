package ru.projectx.clicker.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedIO {
    private static ExecutorService executor;

    public static void init() { ThreadedIO.executor = Executors.newFixedThreadPool(1); }

    public static <T> T submit(Callable<T> job) throws Exception {
        if(ThreadedIO.executor == null || ThreadedIO.executor.isShutdown()) {
            ThreadedIO.init();
        }

        return executor.submit(job).get();
    }
}

