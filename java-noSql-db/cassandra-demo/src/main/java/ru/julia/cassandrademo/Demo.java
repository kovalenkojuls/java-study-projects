package ru.julia.cassandrademo;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.val;
import ru.julia.cassandrademo.model.SmartPhone;
import ru.julia.cassandrademo.schema.CassandraPhonesSchemaInitializer;
import ru.julia.cassandrademo.db.CassandraConnection;
import ru.julia.cassandrademo.db.PhoneRepositoryImpl;
import ru.julia.cassandrademo.model.Phone;

@SuppressWarnings("squid:S106")
public class Demo {

    private static final int CASSANDRA_PORT = 9042;
    private static final String CASSANDRA_HOST = "localhost"; // Работа без DockerToolbox
    // private static final String CASSANDRA_HOST = "192.168.99.100"; // Работа через DockerToolbox

    public static void main(String[] args) {
        val motorolaC350 = new Phone(UUID.randomUUID(), "C350", "silver", "000001");
        val sonyEricssonZ800i = new Phone(UUID.randomUUID(), "Z800i", "silver", "000002");
        val huaweiP20 = new SmartPhone(UUID.randomUUID(), "p20", "black", "000003", "Android");

        try (CassandraConnection connector = new CassandraConnection(CASSANDRA_HOST, CASSANDRA_PORT)) {
            val initializer = new CassandraPhonesSchemaInitializer(connector);
            val repository = new PhoneRepositoryImpl(connector);

            initializer.dropSchemaIfExists();
            initializer.initSchema();

            repository.insert(motorolaC350, Phone.class);
            repository.insert(sonyEricssonZ800i, Phone.class);
            repository.insert(huaweiP20, SmartPhone.class);

            System.out.printf("%n%n");

            val motorolaC350Optional = repository.findOne(motorolaC350.getId(), Phone.class);
            motorolaC350Optional.ifPresent(sm -> System.out.printf("Phone from db is:%n%s", sm));

            System.out.printf("%n%n");

            val motorolaZ800iOptional = repository.findOne(sonyEricssonZ800i.getId(), Phone.class);
            motorolaZ800iOptional.ifPresent(sm -> System.out.printf("Phone from db is:%n%s", sm));

            System.out.printf("%n%n");

            val huaweiP20Optional = repository.findOne(huaweiP20.getId(), SmartPhone.class);
            huaweiP20Optional.ifPresent(sm -> System.out.printf("Smartphone from db is:%n%s", sm));

            System.out.printf("%n%n");

            val allPhones = repository.findAll(SmartPhone.class);
            System.out.printf("All phones from db:%n"
                    + allPhones.stream().map(Objects::toString).collect(Collectors.joining("%n")));
        }
    }
}
