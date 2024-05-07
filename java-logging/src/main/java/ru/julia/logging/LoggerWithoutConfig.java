package ru.julia.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerWithoutConfig {
    private static final Logger logger = LoggerFactory.getLogger(LoggerWithoutConfig.class);

    private void writeLog() {
        var message = "test";
        logger.debug("Debug level: {}", message);
        logger.info("Info level: {}", message);

        /*logger.atInfo().setMessage("Info level:{}")
              .addArgument(() -> message)
              .log();*/

        try {
            throw new RuntimeException();
        } catch (Exception e) {
            logger.error("Error level:", e);
        }
    }

    /* Start without resources/logback.xml */
    public static void main(String[] args) {
        new LoggerWithoutConfig().writeLog();
    }
}
