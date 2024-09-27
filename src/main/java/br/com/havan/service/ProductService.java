package br.com.havan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.havan.exception.ProductNotFound;
import br.com.havan.model.Product;
import br.com.havan.repository.ProductRepository;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){

        return productRepository.findAll();
    }

    public void createProduct(Product request){

        if (request.getName() == null || request.getName().isBlank()) throw new RuntimeException("Valor inválido.");
        if (request.getCode() == null || request.getCode().isBlank()) throw new RuntimeException("Valor inválido.");
        if (request.getPrice() == null || request.getPrice() < 0) throw new RuntimeException("Valor inválido.");
        if (request.getImagePath() == null || request.getImagePath().isBlank()) throw new RuntimeException("Valor inválido.");

        productRepository.save(request);
    }

    public Product getProductById(Long id){
        if(id == null) throw new RuntimeException();
        return productRepository.findById(id)
                .orElseThrow(
                    () -> new ProductNotFound("Product with ID " + id + " not found")
                );
    }


}
