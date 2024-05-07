package ru.julia.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerRollingPolicy {
    private static final Logger logger = LoggerFactory.getLogger(LoggerRollingPolicy.class);
    private long counter = 0;

    private void startLogLoop() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            logger.info("Info level (write to file): {}", counter);
            counter++;
            //Thread.sleep(10);
        }
    }

    /* Copy config-examples/logback_LoggerRollingPolicy.xml -> resources/logback.xml */
    public static void main(String[] args) throws InterruptedException {
        new LoggerRollingPolicy().startLogLoop();
    }
}
