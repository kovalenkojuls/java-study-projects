package ru.julia.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerWithConfig {
    private static final Logger logger = LoggerFactory.getLogger(LoggerWithConfig.class);
    private long counter = 0;

    private void startLogLoop() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            logger.info("Info level: {}", counter);
            logger.error("Error level: {}", counter);
            counter++;
            Thread.sleep(2000);
        }
    }

    /* Copy config-examples/logback_LoggerWithConfig.xml -> resources/logback.xml */
    public static void main(String[] args) throws InterruptedException {
        new LoggerWithConfig().startLogLoop();
    }
}
