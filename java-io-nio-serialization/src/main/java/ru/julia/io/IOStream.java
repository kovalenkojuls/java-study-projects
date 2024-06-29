package ru.julia.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOStream {
    private static final Logger logger = LoggerFactory.getLogger(IOStream.class);

    public static void main(String[] args) throws Exception {
        logger.atInfo().setMessage("current dir: {}")
                .addArgument(() -> System.getProperty("user.dir"))
                .log();

        var ioStream = new IOStream();
        //ioStream.createZipFile("resources/textFile.txt");
        //ioStream.writeObjectToFile("resources/person.bin");
        //ioStream.readObjectFromFile("resources/person.bin");
        ioStream.writeToTextFile("resources/textFile.txt");
        ioStream.readFromTextFile("resources/textFile.txt");

    }

    public void createZipFile(String textFile) throws IOException {
        try (var bufferedInputStream = new BufferedInputStream(new FileInputStream(textFile));
             var zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(textFile + "_copy.zip")))) {

            var zipEntry = new ZipEntry(textFile);
            zipOutputStream.putNextEntry(zipEntry);

            var buffer = new byte[2];
            int size;
            while ((size = bufferedInputStream.read(buffer, 0, buffer.length)) > 0) {
                zipOutputStream.write(buffer, 0, size);
            }

            zipOutputStream.closeEntry();
        }
    }

    public void writeObjectToFile(String personFile) throws IOException {
        try (var objectOutputStream = new ObjectOutputStream(new FileOutputStream(personFile))) {
            var person = new Person(30, "julia", "hiddenField");
            logger.info("serializing: {}", person);
            objectOutputStream.writeObject(person);
        }
    }

    public void readObjectFromFile(String personFile) throws IOException, ClassNotFoundException {
        try (var objectInputStream = new ObjectInputStream(new FileInputStream(personFile))) {
            var person = (Person) objectInputStream.readObject();
            logger.info("deserializing: {}", person);
        }
    }

    public void writeToTextFile(String textFile) throws IOException {
        String line1 = "line 1";
        String line2 = "line 2";

        try (var bufferedWriter = new BufferedWriter(new FileWriter(textFile))) {
            bufferedWriter.write(line1);
            bufferedWriter.newLine();
            bufferedWriter.write(line2);
        }
    }

    public void readFromTextFile(String textFile) throws IOException {
        try (var bufferedReader = new BufferedReader(new FileReader(textFile))) {
            logger.info("text from the file:");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logger.info("{}", line);
            }
        }
    }
}