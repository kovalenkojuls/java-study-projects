package pattern.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerTwo {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerTwo.class);
    private static final Listener listener = data -> logger.info("ConsumerTwo data:{}", data);

    public Listener getListener() {
        return listener;
    }
}