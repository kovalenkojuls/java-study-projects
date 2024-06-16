package pattern.creational.abstractfactory.luminescent;

import pattern.creational.abstractfactory.AbstractFactory;
import pattern.creational.abstractfactory.Bulb;
import pattern.creational.abstractfactory.Lampholder;

public class LuminescentFactory implements AbstractFactory {
    @Override
    public Bulb createBulb() {
        return new BulbLuminescent();
    }

    @Override
    public Lampholder createLampholder() {
        return new LampholderLuminescent();
    }
}
