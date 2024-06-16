package pattern.creational.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws CloneNotSupportedException {
        copyExample();
        cloneExample();
    }

    private static void copyExample() {
        CopyableObject original = new CopyableObject("object1");

        CopyableObject copied = original.copy();
        copied.setName("object2");

        logger.info("original = {}", original);
        logger.info("copied = {}", copied);
        logger.info("original.getName() = {}", original.getName());
        logger.info("copied.getName() = {}", copied.getName());
    }

    private static void cloneExample() throws CloneNotSupportedException {
        ClonableObject original = new ClonableObject("object1");

        ClonableObject cloned = original.clone();
        cloned.setName("object2");

        logger.info("original = {}", original);
        logger.info("cloned = {}", cloned);
        logger.info("original.getName() = {}", original.getName());
        logger.info("cloned.getName() = {}", cloned.getName());
    }
}
