package pattern.creational.factory.factorymethod;

import pattern.creational.factory.Configuration;
import pattern.creational.factory.ConfigurationDB;

class ConfigurationFactoryDB extends ConfigurationFactory {
    @Override
    Configuration buildConfiguration() {
        return new ConfigurationDB();
    }
}