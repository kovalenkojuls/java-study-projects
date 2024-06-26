package pattern.structural.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SDSadapter implements SDSdril {
    private static final Logger logger = LoggerFactory.getLogger(SDSadapter.class);
    private final Drill drill;

    public SDSadapter(Drill drill) {
        this.drill = drill;
    }

    @Override
    public void action() {
        logger.info("{}", this.drill);
    }
}
