package ru.julia.protobuf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ru.julia.protobuf.model.User;

public class RealDBServiceImpl implements RealDBService {
    private final AtomicInteger idCounter;
    private final List<User> users;

    public RealDBServiceImpl() {
        idCounter = new AtomicInteger(0);
        users = new ArrayList<>();
        users.add(new User(idCounter.incrementAndGet(), "Julia", "Kovalenko"));
        users.add(new User(idCounter.incrementAndGet(), "Ivan", "Petrov"));
    }

    @Override
    public User saveUser(String firstName, String lastName) {
        User user = new User(idCounter.incrementAndGet(), firstName, lastName);
        users.add(user);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }
}
