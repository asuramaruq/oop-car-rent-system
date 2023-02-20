package com.company.repositories.interfaces;

import com.company.entities.Client;

import java.util.List;

public interface IClientRepository {
//    Client RegistrationNew(String name, String surname, String username, String password, String telephone);

    Client Registration(Client client);

    Client Sign_in(String username, String password);

//    Client Sign_in(Client client);

    List<Client> getAllClients();

    Client updatePassword(Client client);



}
