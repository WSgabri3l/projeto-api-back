package br.com.havan.model.dto;

//Classe de autenticação 
public class ClientLoginDto {
    
    //Esses atributos serão passados no corpo da autenticação.
    String cpf;
    String password;

    public ClientLoginDto(String cpf, String password) {
        this.cpf = cpf;
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
