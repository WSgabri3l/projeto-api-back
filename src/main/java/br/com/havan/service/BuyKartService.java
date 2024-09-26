package br.com.havan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.havan.model.BuyKart;
import br.com.havan.repository.BuyKartRepository;

@Service
public class BuyKartService {
    
    private final BuyKartRepository buyKartRepository;

    public BuyKartService(BuyKartRepository buyKartRepository) {
        this.buyKartRepository = buyKartRepository;
    }

    public List<BuyKart> getAllBuyKarts(){
        
        return buyKartRepository.findAll();
    }
}
