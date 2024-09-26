package br.com.havan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.havan.model.BuyKart;

@Repository
public interface BuyKartRepository extends JpaRepository<BuyKart, Long> {

}