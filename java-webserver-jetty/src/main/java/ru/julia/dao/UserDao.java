package ru.julia.dao;

import java.util.Optional;
import ru.julia.model.User;

public interface UserDao {

    Optional<User> findById(long id);

    Optional<User> findRandomUser();

    Optional<User> findByLogin(String login);
}
