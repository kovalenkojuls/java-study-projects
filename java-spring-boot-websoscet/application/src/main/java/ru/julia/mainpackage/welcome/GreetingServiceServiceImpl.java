package ru.julia.mainpackage.welcome;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceServiceImpl implements GreetingService {
    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceServiceImpl.class);

    @PostConstruct
    public void printHello() {
        logger.info("Hello from PostConstruct");
    }

    @Override
    public Map<String, String> sayHello(String name) {
        Map<String, String> map = new HashMap<>();
        map.put(name, "Hello, " + name);
        return map;
    }
}
