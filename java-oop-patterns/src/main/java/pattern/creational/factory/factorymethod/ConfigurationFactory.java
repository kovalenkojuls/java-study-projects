package pattern.creational.factory.factorymethod;

import pattern.creational.factory.Configuration;

abstract class ConfigurationFactory {
    //factory method
    abstract Configuration buildConfiguration();

    //simple factory
    static ConfigurationFactory getConfigurationFactory(String param) {
        if ("file".equals(param)) {
            return new ConfigurationFactoryFile();
        }
        if ("db".equals(param)) {
            return new ConfigurationFactoryDB();
        }
        throw new IllegalArgumentException("unknown param:" + param);
    }
}