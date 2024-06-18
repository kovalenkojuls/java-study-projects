package pattern.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        var object = new SomeObject("SomeObject");
        var executor = new Executor(object);

        executor.addCommand(new AdderSomeString());
        executor.addCommand(new Echo());
        executor.addCommand(new AdderSomeString());

        executor.executeCommands();

        logger.info("result object:{}", object);
    }
}
