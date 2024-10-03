package br.com.havan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.havan.model.Product;
import br.com.havan.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FormController {

    private ProductService productService;

    public FormController(ProductService productService){
        this.productService = productService;
    }
    
    @RequestMapping(path = "/registerProduct", method = RequestMethod.POST)
    public String productSubmission(@ModelAttribute @RequestBody Product request){
        log.info(request.getName());
        log.info(request.getCode());
        log.info(request.getPrice().toString());
        log.info(request.getImagePath());

        productService.createProduct(request);
  
        return "redirect:success.html";
    }

}
