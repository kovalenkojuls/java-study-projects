package pattern.creational.factory.factorymethod;

import pattern.creational.factory.Configuration;
import pattern.creational.factory.ConfigurationFile;

class ConfigurationFactoryFile extends ConfigurationFactory {
    @Override
    Configuration buildConfiguration() {
        return new ConfigurationFile();
    }
}