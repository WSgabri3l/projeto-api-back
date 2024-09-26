package br.com.havan.exception;

public class ClientNotFound extends RuntimeException{
    
    public ClientNotFound(String message){
        
        super(message);
    }
}
