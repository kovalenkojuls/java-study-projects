package pattern.creational.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderDemo {
    private static final Logger logger = LoggerFactory.getLogger(BuilderDemo.class);

    public static void main(String[] args) {
        BigObject bigObject2 = new BigObject.Builder("1")
                .withParam2("value of param2")
                .withParam5("value of param5")
                // .withParam4()
                .withParam3("value of param3")
                .build();
        logger.info("{}", bigObject2);

        BigObjectLombok bigObjectLombok = BigObjectLombok
                .builder()
                .param1("x")
                .param4("y")
                .build();

        var changedCopy = bigObjectLombok
                .toBuilder()
                .param5("z")
                .build();

        logger.info("lombok: {}", bigObjectLombok);
        logger.info("copy: {}", changedCopy);
    }
}