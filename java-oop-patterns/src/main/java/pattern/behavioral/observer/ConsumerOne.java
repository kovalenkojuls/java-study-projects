package pattern.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerOne implements Listener {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerOne.class);

    @Override
    public void onUpdate(String data) {
        logger.info("ConsumerA data:{}", data);
    }
}
