package ru.julia.server;

@SuppressWarnings({"squid:S112"})
public interface UsersWebServer {
    void start() throws Exception;

    void join() throws Exception;

    void stop() throws Exception;
}
