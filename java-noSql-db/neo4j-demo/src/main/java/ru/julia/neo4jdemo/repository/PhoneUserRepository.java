package ru.julia.neo4jdemo.repository;

import java.util.List;
import java.util.Optional;

import ru.julia.neo4jdemo.model.PhoneUser;

public interface PhoneUserRepository {
    void insert(PhoneUser phoneUser);

    Optional<PhoneUser> findOne(String id);

    List<PhoneUser> findAll();
}
