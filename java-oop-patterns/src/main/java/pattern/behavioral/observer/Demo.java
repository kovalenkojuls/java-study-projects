package pattern.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        var producer = new Producer();
        var consumerOne = new ConsumerOne();
        var consumerTwo = new ConsumerTwo();

        Listener temp = data -> {
            logger.info("Temp listener:{}", data);
        };

        producer.addListener(temp);
        producer.addListener(consumerOne);
        producer.addListener(consumerTwo.getListener());

        producer.notifyListeners("eventOne");
        producer.notifyListeners("eventTwo");

        producer.removeListener(temp);
        producer.removeListener(consumerOne);
        producer.removeListener(consumerTwo.getListener());

        producer.notifyListeners("eventThree");
        producer.notifyListeners("eventFour");
    }
}