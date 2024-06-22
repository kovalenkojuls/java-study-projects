package pattern.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        Unit marine1 = new Marine();
        Unit marine2 = new Marine();
        Unit marine3 = new Marine();
        Unit tank = new Tank();

        var group = new Group();
        group.addUnit(marine1);
        group.addUnit(marine2);
        group.addUnit(marine3);
        group.addUnit(tank);

        logger.info("first group:");
        group.move();

        var group2 = new Group();
        group2.addUnit(group);
        group2.addUnit(new Tank());

        logger.info("second group:");
        group2.move();
    }
}
