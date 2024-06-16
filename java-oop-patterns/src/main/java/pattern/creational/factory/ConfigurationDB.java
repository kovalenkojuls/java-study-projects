package pattern.creational.factory;

public class ConfigurationDB implements Configuration {
    @Override
    public String params() {
        return "params from DB";
    }
}