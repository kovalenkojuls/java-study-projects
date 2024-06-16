package pattern.creational.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonLazy {
    private static final Logger logger = LoggerFactory.getLogger(SingletonLazy.class);
    private static SingletonLazy instance = null;

    private SingletonLazy() {
        logger.info("run constructor");
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            // плохо, можно получить два экземпляра
            logger.info("lazy init 1");
            instance = new SingletonLazy();
        }

        return instance;
    }

    public static synchronized SingletonLazy getInstance2() {
        // хорошо, но медленно
        if (instance == null) {
            logger.info("lazy init 2");
            instance = new SingletonLazy();
        }

        return instance;
    }

    public static SingletonLazy getInstance3() {
        //Не работает потому что https://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
        if (instance == null) {
            synchronized (SingletonLazy.class) {
                if (instance == null) {
                    logger.info("lazy init 3");
                    instance = new SingletonLazy();
                }
            }
        }

        return instance;
    }
}

class SingletonLazyDemo {
    private static final Logger logger = LoggerFactory.getLogger(SingletonLazyDemo.class);

    public static void main(String[] args) {
        logger.info("--- begin ---");

        SingletonLazy singleton1 = SingletonLazy.getInstance();
        SingletonLazy singleton2 = SingletonLazy.getInstance();

        logger.info("{}", singleton1);
        logger.info("{}", singleton2);
        logger.info("singleton1 == singleton2 => {}\n", singleton1 == singleton2);
        logger.info("---end ---");
    }
}