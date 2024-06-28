package ru.julia.IOStream;

import java.io.Serial;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Person.class);
    @Serial
    private static final long serialVersionUID = 1L;
    private final int age;
    private final String name;
    private final transient String hidden;

    Person(int age, String name, String hidden) {
        logger.info("new Person");
        this.age = age;
        this.name = name;
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return String.format("Person{age=%d, name=%s, hidden=%s}", age, name, hidden);
    }
}
