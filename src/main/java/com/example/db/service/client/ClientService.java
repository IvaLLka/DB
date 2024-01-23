package com.example.db.service.client;

import com.example.db.model.Automobile;
import com.example.db.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {

    public Client getClientByID(Long id);
    public Client writeClient(Client client);
    void editClient(Long id, Client client);
    public String deleteClient(Long id);
    public List<Client> getAllClients();

    public Client getClientById(Long id);
}
