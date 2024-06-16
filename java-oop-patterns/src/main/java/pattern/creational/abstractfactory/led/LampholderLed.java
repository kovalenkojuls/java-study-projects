package pattern.creational.abstractfactory.led;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pattern.creational.abstractfactory.Lampholder;

public class LampholderLed implements Lampholder {
    private static final Logger logger = LoggerFactory.getLogger(LampholderLed.class);

    @Override
    public void hold() {
        logger.info("Led hold");
    }
}
