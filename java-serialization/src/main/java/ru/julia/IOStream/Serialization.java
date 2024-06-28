package ru.julia.IOStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Serialization {
    private static final Logger logger = LoggerFactory.getLogger(Serialization.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        deserialize(serialize());
    }

    private static byte[] serialize() throws IOException {
        try (var byteArrayOutputStream = new ByteArrayOutputStream();
             var objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

            var person = new Person(1, "person", "hidden");
            logger.info("serializing: {}", person);
            objectOutputStream.writeObject(person);

            return byteArrayOutputStream.toByteArray();
        }
    }

    private static void deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (var objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            var person = (Person) objectInputStream.readObject();
            logger.info("deserialized person: {}", person);
        }
    }
}