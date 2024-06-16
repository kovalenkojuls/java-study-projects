package pattern.creational.factory.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pattern.creational.factory.Configuration;

public class FactoryMethodDemo {
    private static final Logger logger = LoggerFactory.getLogger(FactoryMethodDemo.class);

    public static void main(String[] args) {
        ConfigurationFactory factory1 = ConfigurationFactory.getConfigurationFactory("file");
        Configuration config1 = factory1.buildConfiguration();
        readData(config1);

        ConfigurationFactory factory2 = ConfigurationFactory.getConfigurationFactory("db");
        Configuration config2 = factory2.buildConfiguration();
        readData(config2);
    }

    private static void readData(Configuration config) {
        logger.atInfo().setMessage("{}").addArgument(config::params).log();
    }
}