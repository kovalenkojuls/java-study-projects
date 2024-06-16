package pattern.creational.abstractfactory.led;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pattern.creational.abstractfactory.Bulb;

public class BulbLed implements Bulb {
    private static final Logger logger = LoggerFactory.getLogger(BulbLed.class);

    @Override
    public void light() {
        logger.info("Led light");
    }
}
