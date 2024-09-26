package br.com.havan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.havan.exception.ClientNotFound;
import br.com.havan.model.Client;
import br.com.havan.repository.ClientRepository;

@Service
public class ClientService {
    
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients(){
        
        return clientRepository.findAll();
    }

    public void createClient(Client request){

        if(verification(request)){
            request.setCpf(request.getCpf().replaceAll("[^0-9]", ""));
            request.setCep(request.getCep().replaceAll("[^0-9]", ""));
            clientRepository.save(request);
        }
    }

    public Client getClientById(Long id){
        if(id == null) throw new IllegalArgumentException();
        return clientRepository.findById(id)
                .orElseThrow(
                    () -> new ClientNotFound("Client with ID " + id + " not found")
                );
    }

    public void updateClient(Long id, Client request){

        if (verification(request)){
            Client client = clientRepository.findById(id)
            .orElseThrow(
                () -> new ClientNotFound("Client not found")
            );

            client.setName(request.getName());
            client.setEmail(request.getEmail());
            client.setCpf(request.getCpf());
            client.setCep(request.getCep());

            clientRepository.save(client);
        }
    }

    public void deleteClient(Long id){
        if(id == null) throw new IllegalArgumentException();
        this.getClientById(id);
        clientRepository.deleteById(id);
    }

    private Boolean verification(Client request){

        if(request.getName() == null || request.getName().isBlank()) throw new IllegalArgumentException();
        if(request.getEmail() == null || request.getEmail().isBlank()) throw new IllegalArgumentException();
        if(request.getPassword() == null || request.getPassword().isBlank()) throw new IllegalArgumentException();
        if(request.getCpf() == null || request.getCpf().isBlank()) throw new IllegalArgumentException();
        if(request.getCep() == null || request.getCep().isBlank()) throw new IllegalArgumentException();

        return true;
    }



}
