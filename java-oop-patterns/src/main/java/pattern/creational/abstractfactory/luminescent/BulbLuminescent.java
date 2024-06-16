package pattern.creational.abstractfactory.luminescent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pattern.creational.abstractfactory.Bulb;

public class BulbLuminescent implements Bulb {
    private static final Logger logger = LoggerFactory.getLogger(BulbLuminescent.class);

    @Override
    public void light() {
        logger.info("Luminescent light");
    }
}
