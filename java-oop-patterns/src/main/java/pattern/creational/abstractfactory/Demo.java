package pattern.creational.abstractfactory;

import pattern.creational.abstractfactory.led.LedFactory;
import pattern.creational.abstractfactory.luminescent.LuminescentFactory;

public class Demo {
    public static void main(String[] args) {
        var demo = new Demo();

        AbstractFactory ledFactory = getFactory("Led");
        demo.run(ledFactory);

        AbstractFactory luminescentFactory = getFactory("Luminescent");
        demo.run(luminescentFactory);
    }

    public void run(AbstractFactory abstractFactory) {
        Bulb bulb = abstractFactory.createBulb();
        Lampholder lampholder = abstractFactory.createLampholder();

        bulb.light();
        lampholder.hold();
    }

    public static AbstractFactory getFactory(String param) {
        if ("Led".equals(param)) {
            return new LedFactory();
        }
        if ("Luminescent".equals(param)) {
            return new LuminescentFactory();
        }
        throw new IllegalArgumentException("unknown param:" + param);
    }
}
