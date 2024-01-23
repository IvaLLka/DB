package com.example.db.service.client;

import com.example.db.model.Client;
import com.example.db.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientByID(Long id) {
        return clientRepository.findClientByID(id);
    }

    @Override
    public Client writeClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void editClient(Long id, Client client) {
        Client newClient = clientRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Client is not found"));
        newClient.setAdress(client.getAdress());
        newClient.setDob(client.getDob());
        newClient.setRegistrationDate(client.getRegistrationDate());
        newClient.setFullName(client.getFullName());
        newClient.setPhoneNumber(client.getPhoneNumber());
        newClient.setSales(client.getSales());
        clientRepository.save(newClient);
    }

    @Override
    public String deleteClient(Long id) {
        clientRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Client is not found"));
    }
}
