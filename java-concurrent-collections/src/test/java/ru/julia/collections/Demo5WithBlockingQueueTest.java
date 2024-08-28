package ru.julia.collections;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;

class Demo5WithBlockingQueueTest {
    private static final int ITERATIONS_COUNT = 1000;

    @Test
    void testBlockingQueueWorksGreat() throws InterruptedException {
        var list = new ArrayBlockingQueue<>(10);
        final CountDownLatch latch = new CountDownLatch(2);
        List<Exception> exceptions = new CopyOnWriteArrayList<>();

        Thread t1 = new Thread(() -> {
            try {
                latch.countDown();
                latch.await();
                for (int i = 0; i < ITERATIONS_COUNT; i++) {
                    list.take();
                }
            } catch (Exception ex) {
                exceptions.add(ex);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                latch.countDown();
                latch.await();
                for (int i = 0; i < ITERATIONS_COUNT; i++) {
                    list.put(i);
                }
            } catch (Exception ex) {
                exceptions.add(ex);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertThat(exceptions).withFailMessage(exceptions.toString()).isEmpty();
    }
}
