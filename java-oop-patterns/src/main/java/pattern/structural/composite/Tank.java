package pattern.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tank implements Unit {
    private static final Logger logger = LoggerFactory.getLogger(Tank.class);

    @Override
    public void move() {
        logger.info("Tank is moving");
    }
}
