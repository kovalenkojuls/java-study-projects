package pattern.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final List<Listener> listeners = new ArrayList<>();

    void addListener(Listener listener) {
        listeners.add(listener);
    }

    void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    void notifyListeners(String data) {
        for (int i = 0; i < listeners.size(); ++i) {
            try {
                listeners.get(i).onUpdate(data);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}