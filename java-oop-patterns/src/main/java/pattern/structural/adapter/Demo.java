package pattern.structural.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        new Demo().usePattern();
    }

    public void usePattern() {
        var rotaryHammer = new RotaryHammer();
        var drill = new Drill();
        rotaryHammer.drill(new SDSadapter(drill));
    }
}
