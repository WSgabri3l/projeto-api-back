package br.com.havan.exception;

public class ProductNotFound extends RuntimeException{

    public ProductNotFound(String message){

        super(message);
    }
    
}
