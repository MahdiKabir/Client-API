package com.example.demo.client;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public void addNewClient(Client client) {
        Optional<Client> clientOptional = clientRepository
                .findByEmail(client.getEmail());

        if(clientOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }

        clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
        boolean exists = clientRepository.existsById(clientId);

        if(!exists) {
            throw new IllegalStateException(
                    "client with id " + clientId + " does not exist"
            );
        }

        clientRepository.deleteById(clientId);
    }

    @Transactional
    public void updateClient(Long clientId,
                             String name,
                             String email) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalStateException(
                        "client with id " + clientId + " does not exist"
                ));

        if(name != null
                && !name.isEmpty()
                && !Objects.equals(client.getName(), name)) {
            client.setName(name);
        }

        if(email != null
                && !email.isEmpty()
                && !Objects.equals(client.getEmail(), email)) {

            Optional<Client> clientOptional = clientRepository
                    .findByEmail(client.getEmail());

            if(clientOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }

            client.setEmail(email);
        }
    }
}
