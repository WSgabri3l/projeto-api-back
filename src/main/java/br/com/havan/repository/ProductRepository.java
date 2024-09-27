package br.com.havan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.havan.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}