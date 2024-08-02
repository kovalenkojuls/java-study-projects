package ru.julia.services;

import java.util.List;
import ru.julia.domain.Client;

public interface ClientService {
    List<Client> findAll();

    Client findById(long id);

    Client findByName(String name);

    Client findRandom();

    Client save(Client client);
}
