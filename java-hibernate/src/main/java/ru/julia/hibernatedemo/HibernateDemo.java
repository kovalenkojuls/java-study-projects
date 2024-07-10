package ru.julia.hibernatedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.julia.core.HibernateUtils;
import ru.julia.hibernatedemo.model.*;

public class HibernateDemo {
    private static final Logger logger = LoggerFactory.getLogger(HibernateDemo.class);

    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtils.buildSessionFactory(
                OtusStudent.class,
                Avatar.class,
                EMail.class,
                Course.class)) {
            logger.info("Statistics:{}", sessionFactory.getStatistics());
        }
    }
}
