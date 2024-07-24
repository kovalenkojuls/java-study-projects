package ru.julia.cassandrademo.schema;

public interface CassandraSchemaInitializer {
    void initSchema();

    void dropSchemaIfExists();
}
