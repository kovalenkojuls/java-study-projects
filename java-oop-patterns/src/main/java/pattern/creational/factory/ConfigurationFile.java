package pattern.creational.factory;

public class ConfigurationFile implements Configuration {
    @Override
    public String params() {
        return "params from file";
    }
}