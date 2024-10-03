package br.com.havan.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Email 
    @Column(unique = true)
    private String email;
    private String password;

    @CPF
    @Column(unique = true)
    private String cpf;
    private String cep;

    public Client(String name, String email, String password, String cpf, String cep) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.cep = cep;
    }

}
