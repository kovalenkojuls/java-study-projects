package ru.julia.mongodemo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.val;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import ru.julia.mongodemo.model.Phone;
import ru.julia.mongodemo.model.SmartPhone;
import ru.julia.mongodemo.template.MongoTemplateImpl;

@SuppressWarnings({"squid:S106", "squid:S125", "squid:S2142"})
public class Demo {
    private static final String MONGODB_URL = "mongodb://localhost:30001"; // Работа без DockerToolbox
    // private static final String MONGODB_URL = "mongodb://192.168.99.100:30001"; // Работа через DockerToolbox

    private static final String MONGO_DATABASE_NAME = "mongo-db-test";

    private static final String PHONES_COLLECTION_NAME = "phones";

    public static void main(String[] args) {
        val motorolaC350 = new Phone(null, "C350", "silver", "000001");
        val sonyEricssonZ800i = new Phone(null, "Z800i", "silver", "000002");
        val huaweiP20 = new SmartPhone(null, "p20", "black", "000003", "Android");

        ConnectionString connectionString = new ConnectionString(MONGODB_URL);
        CodecProvider pojoCodecProvider =
                PojoCodecProvider.builder().register("ru.julia.mongodemo.model").build();
        CodecRegistry pojoCodecRegistry =
                fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(pojoCodecRegistry)
                .build();

        try (val mongoClient = MongoClients.create(clientSettings)) {
            val database = mongoClient.getDatabase(MONGO_DATABASE_NAME);
            val collection = database.getCollection(PHONES_COLLECTION_NAME);

            database.drop();

            val mongoTemplate = new MongoTemplateImpl(PHONES_COLLECTION_NAME, database);
            mongoTemplate.insert(motorolaC350);
            mongoTemplate.insert(sonyEricssonZ800i);
            mongoTemplate.insert(huaweiP20);

            val huaweiP20Optional = mongoTemplate.findOne(huaweiP20.getId(), SmartPhone.class);
            huaweiP20Optional.ifPresent(sm -> System.out.printf("Smartphone from db is:%n%s", sm));

            System.out.printf("%n%n");

            // val allSilverPhones = mongoTemplate.find(eq("color", "silver"), Phone.class);
            val allSilverPhones = mongoTemplate.find(Document.parse("{\"color\": \"silver\"}"), Phone.class);
            System.out.printf("All sliver phones from db:%n"
                    + allSilverPhones.stream().map(Objects::toString).collect(Collectors.joining("%n")));

            System.out.println(":%n:%n");

            collection.insertOne(
                    Document.parse("{\"model\":\"Sasha Stetsenko\", \"color\": \"white\", \"parent\": \"Vasya\"}"));

            val allPhones = mongoTemplate.findAll(Phone.class);
            System.out.printf("All phones from db:%n"
                    + allPhones.stream().map(Objects::toString).collect(Collectors.joining("%n")));

            System.out.println();
            System.out.println();
        }
    }
}
