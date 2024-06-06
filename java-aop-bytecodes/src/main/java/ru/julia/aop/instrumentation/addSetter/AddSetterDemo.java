package ru.julia.aop.instrumentation.addSetter;

import java.lang.reflect.InvocationTargetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
> java -javaagent:addSetterDemo.jar -jar addSetterDemo.jar
*/
public class AddSetterDemo {
    private static final Logger logger = LoggerFactory.getLogger(AddSetterDemo.class);

    public static void main(String[] args) throws Exception {
        logger.info("main");
        var demo = new MyClass();

        logger.info("{}", demo.getValue());
        modifyPrivateValue(demo);
        logger.info("{}", demo.getValue());
    }

    private static void modifyPrivateValue(MyClass demo)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        demo.getClass()
             .getMethod("setValue", int.class)
             .invoke(demo, -4);
    }
}