package br.com.uniamerica.imobiliaria.Servise;

import br.com.uniamerica.imobiliaria.Entity.Admin;
import br.com.uniamerica.imobiliaria.Entity.Buyer;
import br.com.uniamerica.imobiliaria.Repository.BuyerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BuyerServise {



    @Autowired
    private BuyerRepository buyerRepository;


    public List<Buyer> listar(){

        return buyerRepository.findAll();
    }



    public Buyer cadastrar(Buyer buyer) {
        if (buyer.getName().trim().isEmpty()) {
            throw new RuntimeException("Erro: BUYER NULA");

        } else {
            return this.buyerRepository.save(buyer);
        }
    }

    @Transactional
    public void atualizar(Long id,Buyer buyer){
        if (id ==  buyer.getId()){
            this.buyerRepository.save(buyer);
        }else {
            throw new RuntimeException();
        }

    }



    @Transactional
    public void desativar(Long id){
        var admin = this.buyerRepository.findById(id);
        if (id == admin.get().getId()){
            this.buyerRepository.desativar(id);
        }
        else {
            throw new RuntimeException();
        }
    }


















}
