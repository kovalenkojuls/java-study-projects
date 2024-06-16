package pattern.creational.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonJava {
    private static final Logger logger = LoggerFactory.getLogger(SingletonJava.class);

    private SingletonJava() {
        logger.info("lazy creation");
    }

    static SingletonJava getInstance() {
        logger.info("getInstance");
        return SingletonJavaHolder.instance;
    }

    private static class SingletonJavaHolder {
        static {
            logger.info("init SingletonJavaHolder");
        }

        static final SingletonJava instance = new SingletonJava();
    }
}

class SingletonJavaDemo {
    private static final Logger logger = LoggerFactory.getLogger(SingletonJavaDemo.class);

    public static void main(String[] args) {
        logger.info("--- begin ---");
        logger.info("- singleton 1");
        SingletonJava singleton1 = SingletonJava.getInstance();

        logger.info("");
        logger.info("- singleton 2");
        SingletonJava singleton2 = SingletonJava.getInstance();

        logger.info("");
        logger.info("singleton1 == singleton2 => {}\n", singleton1 == singleton2);
        logger.info("{}", singleton1);
        logger.info("{}", singleton2);
        logger.info("---end ---");
    }
}