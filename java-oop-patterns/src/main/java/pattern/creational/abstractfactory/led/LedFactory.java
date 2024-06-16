package pattern.creational.abstractfactory.led;

import pattern.creational.abstractfactory.AbstractFactory;
import pattern.creational.abstractfactory.Bulb;
import pattern.creational.abstractfactory.Lampholder;

public class LedFactory implements AbstractFactory {
    @Override
    public Bulb createBulb() {
        return new BulbLed();
    }

    @Override
    public Lampholder createLampholder() {
        return new LampholderLed();
    }
}
