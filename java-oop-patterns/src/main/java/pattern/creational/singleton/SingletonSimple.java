package pattern.creational.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonSimple {
    private static final Logger logger = LoggerFactory.getLogger(SingletonSimple.class);
    private static final SingletonSimple instance = new SingletonSimple();

    private SingletonSimple() {
        logger.info("run constructor");
    }

    public static SingletonSimple getInstance() {
        return instance;
    }
}

class SingletonSimpleDemo {
    private static final Logger logger = LoggerFactory.getLogger(SingletonSimpleDemo.class);

    public static void main(String[] args) {
        logger.info("--- begin ---");

        SingletonSimple singleton1 = SingletonSimple.getInstance();
        SingletonSimple singleton2 = SingletonSimple.getInstance();

        logger.info("{}", singleton1);
        logger.info("{}", singleton2);

        logger.info("singleton1 == singleton2 => {}\n", singleton1 == singleton2);
        logger.info("---end ---");
    }
}