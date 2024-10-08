package br.com.havan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.havan.model.Product;
import br.com.havan.service.ProductService;

/* Notas
 * @Controller retorna páginas.
 * @RestController retorna páginas e responses bodies.
*/

@RestController
@RequestMapping("/product/v1")
public class ProductController {
    
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){

        return productService.getProductById(id);
    }

    @PostMapping
    public void saveProduct(@RequestBody Product request){

        productService.createProduct(request);
    }
}
