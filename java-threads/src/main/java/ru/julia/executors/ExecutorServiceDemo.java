package ru.julia.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("java:S125")
public class ExecutorServiceDemo {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceDemo.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
         new ExecutorServiceDemo().singleThread();
//         new ExecutorServiceDemo().newFixedThreadPool();
//         new ExecutorServiceDemo().scheduledThreadPoolExecutor();
    }

    private String task(int id) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("call is done:{}", id);
        return "done " + id;
    }

    void singleThread() throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        var resultInFuture1 = executor.submit(() -> task(1));
        logger.info("task1 submitted");

        var resultInFuture2 = executor.submit(() -> task(2));
        logger.info("task2 submitted");

        var resultInFuture3 = executor.submit(() -> task(3));
        logger.info("task3 submitted");

        var result1 = resultInFuture1.get();
        var result2 = resultInFuture2.get();
        var result3 = resultInFuture3.get();

        logger.info("result1:{}", result1);
        logger.info("result2:{}", result2);
        logger.info("result3:{}", result3);

        executor.shutdown();
    }

    void newFixedThreadPool() throws ExecutionException, InterruptedException {
        var executor = Executors.newFixedThreadPool(1);

        var resultInFuture1 = executor.submit(() -> task(1));
        logger.info("task1 submitted");

        var resultInFuture2 = executor.submit(() -> task(2));
        logger.info("task2 submitted");

        var resultInFuture3 = executor.submit(() -> task(3));
        logger.info("task3 submitted");

        var result1 = resultInFuture1.get();
        var result2 = resultInFuture2.get();
        var result3 = resultInFuture3.get();

        logger.info("result1:{}", result1);
        logger.info("result2:{}", result2);
        logger.info("result3:{}", result3);

        executor.shutdown();
    }

    void scheduledThreadPoolExecutor() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(() -> logger.info("task is done 1"), 0, 3, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(() -> logger.info("task is done 2"), 0, 2, TimeUnit.SECONDS);
    }
}