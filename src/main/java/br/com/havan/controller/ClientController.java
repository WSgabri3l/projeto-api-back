package br.com.havan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.havan.model.Client;
import br.com.havan.model.dto.ClientLoginDto;
import br.com.havan.service.ClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/client/v1")
public class ClientController {
    
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientService.getClientById(id);
    }

    //Retorna todas as informações 
    @PostMapping("/login")
    public String login(@RequestBody ClientLoginDto loginRequest){
        return clientService.loginByToken(loginRequest);
    }

    @PostMapping
    public void saveClient(@Valid  @RequestBody Client request){

        clientService.createClient(request);
    }

    @PutMapping("/{id}")
    public void updateClient(@PathVariable Long id, @RequestBody Client requestUpdate){

        clientService.updateClient(id , requestUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id, @RequestBody Client requestUpdate){

        clientService.deleteClient(id);
    }
}
