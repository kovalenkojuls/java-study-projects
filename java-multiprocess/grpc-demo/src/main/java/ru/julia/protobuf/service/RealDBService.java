package ru.julia.protobuf.service;

import java.util.List;
import ru.julia.protobuf.model.User;

public interface RealDBService {
    User saveUser(String firstName, String lastName);

    List<User> findAllUsers();
}
