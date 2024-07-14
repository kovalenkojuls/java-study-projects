package ru.julia.core;

import java.util.Arrays;
import java.util.function.Consumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public final class HibernateUtils {

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    private HibernateUtils() {}

    public static SessionFactory buildSessionFactory(Configuration configuration, Class<?>... annotatedClasses) {
        MetadataSources metadataSources = new MetadataSources(createServiceRegistry(configuration));
        Arrays.stream(annotatedClasses).forEach(metadataSources::addAnnotatedClass);

        Metadata metadata = metadataSources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory buildSessionFactory(String configResourceName, Class<?>... annotatedClasses) {
        Configuration configuration = new Configuration().configure(configResourceName);
        configuration.setProperty("hibernate.hbm2ddl.auto", "create"); // only for study
        return buildSessionFactory(configuration, annotatedClasses);
    }

    public static SessionFactory buildSessionFactory(Class<?>... annotatedClasses) {
        return buildSessionFactory(HIBERNATE_CFG_FILE, annotatedClasses);
    }

    private static StandardServiceRegistry createServiceRegistry(Configuration configuration) {
        return new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
    }

    public static void doInSession(SessionFactory sf, Consumer<Session> action) {
        try (Session session = sf.openSession()) {
            action.accept(session);
        }
    }

    public static void doInSessionWithTransaction(SessionFactory sf, Consumer<Session> action) {
        try (Session session = sf.openSession()) {
            Transaction t = session.getTransaction();
            t.begin();
            try {
                action.accept(session);
                t.commit();
            } catch (Exception e) {
                t.rollback();
                throw e;
            }
        }
    }
}