package ru.julia.services;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}
