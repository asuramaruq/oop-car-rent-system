package com.company.controllers;

import com.company.entities.Car;
import com.company.entities.Client;
import com.company.repositories.interfaces.IClientRepository;

import java.util.List;

public class ClientController {
    private IClientRepository repository;
    public ClientController(IClientRepository repository){
        this.repository = repository;
    }

    public Client Registration(Client client){
        return repository.Registration(client);
    }

//    public Client RegistrationNew(String name, String surname, String username, String password, String telephone){
//        return repository.RegistrationNew(name, surname, username, password, telephone);
//    }

    public Client Sign_in(String telephoneNumber, String password) {
        return repository.Sign_in(telephoneNumber, password);
    }

//    public Client Sign_inNEW(Client client) {
//        return repository.Sign_in(client);
//    }

    public List<Client> getAllClients(){
        return repository.getAllClients();
    }
}
