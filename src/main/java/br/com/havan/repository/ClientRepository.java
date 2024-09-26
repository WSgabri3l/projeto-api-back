package br.com.havan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.havan.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
} 