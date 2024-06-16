package pattern.creational.factory.simplefactory;

import pattern.creational.factory.Configuration;
import pattern.creational.factory.ConfigurationDB;
import pattern.creational.factory.ConfigurationFile;

class ConfigurationFactory {
    static Configuration getConfiguration(String param) {
        if ("file".equals(param)) {
            return new ConfigurationFile();
        }

        if ("db".equals(param)) {
            return new ConfigurationDB();
        }

        throw new IllegalArgumentException("unknown param:" + param);
    }
}