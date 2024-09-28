package br.com.havan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.havan.exception.ClientNotFound;
import br.com.havan.model.Client;
import br.com.havan.model.dto.ClientLoginDto;
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

    public String loginByToken(ClientLoginDto request){

        Client client = clientRepository.findByCpf(request.getCpf())
        .orElseThrow(
            () -> new ClientNotFound("Client not found")
        );

        if (!client.getPassword().equals(request.getPassword())) throw new RuntimeException("Acesso Negado");

        return "Acesso Liberado";
    }

    //Login
    //O DTO serve como corpo de requisição para executar buscas.
    //Uma vez encontrada a linha com o valor que bate com o CPF, faremos uma verificação.
    //Esta consiste em comparar a senha dada pelo usuário com a senha presente na linha.
    //E assim retornar essa linha (Client).
    public Client loginGetData(ClientLoginDto request){

        Client client = clientRepository.findByCpf(request.getCpf())
            .orElseThrow(
                () -> new ClientNotFound("Client not found")
            );

        if (!client.getPassword().equals(request.getPassword())) throw new RuntimeException("Senha ou CPF inválido");

        return client;
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
