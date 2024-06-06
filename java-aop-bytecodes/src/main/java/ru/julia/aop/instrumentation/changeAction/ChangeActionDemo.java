package ru.julia.aop.instrumentation.changeAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
> java -javaagent:changeActionDemo.jar -jar changeActionDemo.jar
*/
public class ChangeActionDemo {
    private static final Logger logger = LoggerFactory.getLogger(ChangeActionDemo.class);

    public static void main(String[] args) {
        var myClass = new MyClass();
        logger.info("{}", myClass.summator(30, 20));
    }
}
