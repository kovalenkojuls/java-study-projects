package ru.julia.tableineritance;

import jakarta.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import org.h2.tools.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.julia.core.HibernateUtils;
import ru.julia.tableineritance.model.A;
import ru.julia.tableineritance.model.B;
import ru.julia.tableineritance.model.C;

public class TableInheritanceDemo {
    private static final Logger logger = LoggerFactory.getLogger(TableInheritanceDemo.class);
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) throws SQLException {
        try (var sf = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE, A.class, B.class, C.class)) {
            logger.info("\n\n-----------------------------------------------------\n\n");
            logger.info("Start insert A/B/C: ");
            try (var session = sf.openSession()) {

                var a = new A(0, "A_1");
                var b = new B(0, "A_2", "B_2");
                var c = new C(0, "A_3", "C_3");

                session.getTransaction().begin();
                session.persist(a);
                session.persist(b);
                session.persist(c);
                session.getTransaction().commit();

                logger.info("\n\n-----------------------------------------------------\n\n");
                logger.info("Select all entities A/B/C:");
                session.getTransaction().begin();
                TypedQuery<A> query = session.createQuery("select fieldA from A a", A.class);
                List<A> resultList = query.getResultList();

                logger.info("\n\nResult: {}", resultList);
                session.getTransaction().commit();
            }
            Console.main();
        }
    }
}
