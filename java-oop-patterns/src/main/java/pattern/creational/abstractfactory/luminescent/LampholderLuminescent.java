package pattern.creational.abstractfactory.luminescent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pattern.creational.abstractfactory.Lampholder;

public class LampholderLuminescent implements Lampholder {
    private static final Logger logger = LoggerFactory.getLogger(LampholderLuminescent.class);

    @Override
    public void hold() {
        logger.info("Luminescent hold");
    }
}
