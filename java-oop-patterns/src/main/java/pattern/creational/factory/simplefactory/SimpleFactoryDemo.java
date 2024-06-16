package pattern.creational.factory.simplefactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pattern.creational.factory.Configuration;

import java.util.*;

public class SimpleFactoryDemo {
    private static final Logger logger = LoggerFactory.getLogger(SimpleFactoryDemo.class);

    public static void main(String[] args) {
        Configuration config1 = ConfigurationFactory.getConfiguration("file");
        readData(config1);

        Configuration config2 = ConfigurationFactory.getConfiguration("db");
        readData(config2);
    }

    private static void readData(Configuration config) {
        logger.atInfo().setMessage("{}").addArgument(config::params).log();
    }

    private static void exampleFromJDK() {
        var str1 = String.valueOf(1);
        var str2 = String.valueOf(true);
        var str3 = String.valueOf('a');

        var opt1 = Optional.empty();
        var otp2 = Optional.of("string");
        var opt3 = Optional.ofNullable(null);

        Collection<?> originalCollection = null;
        List<Integer> originalList = null;
        Map<String, Integer> originalMap = null;
        var syncedCollection = Collections.synchronizedCollection(originalCollection);
        var syncedSet = Collections.synchronizedSet(new HashSet<>());
        var unmodifiableList = Collections.unmodifiableList(originalList);
        var unmodifiableMap = Collections.unmodifiableMap(originalMap);
    }
}