package ru.julia.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JacksonDemo {
    private static final Logger logger = LoggerFactory.getLogger(JacksonDemo.class);
    private final ObjectMapper mapper;
    private final User user = new User(
            30,
            "Julia",
            null,
            LocalDateTime.now(),
            "someData");

    public static void main(String[] args) throws IOException {
        var fileName = "resources/jack.json";

        var demo = new JacksonDemo();
        demo.writeUserToFile(fileName);
        demo.loadUserFromFile(fileName);

        var userAsString = demo.writeUserAsString();
        logger.info("userAsString:{}", userAsString);
        logger.info("user from string:{}", demo.readUserFromString(userAsString));
        logger.info("propertyValue:{}",
                demo.readUserPropValue(userAsString, "nameForSerialization"));

        demo.innerObject();
    }

    public JacksonDemo() {
        this.mapper = JsonMapper.builder().build();
        mapper.registerModule(new JavaTimeModule());
    }

    private void writeUserToFile(String fileName) throws IOException {
        var file = new File(fileName);
        mapper.writeValue(file, user);
        logger.info("user write to the file: {}", file.getAbsolutePath());
    }

    private void loadUserFromFile(String fileName) throws IOException {
        var file = new File(fileName);
        var userLoaded = mapper.readValue(file, User.class);
        logger.info("user loaded from the file: {}, user: {}", file.getAbsolutePath(), userLoaded);
    }

    private String writeUserAsString() throws JsonProcessingException {
        return mapper.writeValueAsString(user);
    }

    private User readUserFromString(String userAsString) throws JsonProcessingException {
        return mapper.readValue(userAsString, User.class);
    }

    private String readUserPropValue(String userAsString, String nameForSerialization) throws JsonProcessingException {
        JsonNode node = mapper.readTree(userAsString);
        JsonNode jsonProp = node.get(nameForSerialization);
        return jsonProp.textValue();
    }

    private void innerObject() throws JsonProcessingException {
        var userAddr = new User(
                30,
                "julia",
                new Address("Moscow", "Arbat"),
                LocalDateTime.now(),
                "transientProperty");

        var userPhone = new User(
                30,
                "julia",
                new Phone("+7-800-555-35-35"),
                LocalDateTime.now(),
                "transientProperty");
        
        var userAddrAsString = mapper.writeValueAsString(userAddr);
        var userAddrFromString = mapper.readValue(userAddrAsString, User.class);
        logger.info("userAddrAsString:{}", userAddrAsString);
        logger.info("userAddrFromString:{}", userAddrFromString);
        
        var userPhoneAsString = mapper.writeValueAsString(userPhone);
        var userPhoneFromString = mapper.readValue(userPhoneAsString, User.class);
        logger.info("userPhoneAsString:{}", userPhoneAsString);
        logger.info("userPhoneFromString:{}", userPhoneFromString);
    }
}
