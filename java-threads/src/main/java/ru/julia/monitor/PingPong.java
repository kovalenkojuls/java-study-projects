package ru.julia.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingPong {
    private static final Logger logger = LoggerFactory.getLogger(PingPong.class);
    private String last = "PONG";

    private void action(String message) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
                while (last.equals(message)) {
                    this.wait();
                }

                logger.info(message);
                last = message;
                sleep();
                notifyAll();
                logger.info("after notify");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        new Thread(() -> pingPong.action("PONG")).start();
        sleepHalf();
        new Thread(() -> pingPong.action("ping")).start();

    }

    private static void sleepHalf() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
