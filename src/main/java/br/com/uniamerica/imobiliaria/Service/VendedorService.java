package br.com.uniamerica.imobiliaria.Service;

import br.com.uniamerica.imobiliaria.Entity.Apartamento;
import br.com.uniamerica.imobiliaria.Entity.Vendedor;
import br.com.uniamerica.imobiliaria.Repository.ApartamentoRepository;
import br.com.uniamerica.imobiliaria.Repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class VendedorService {

    @Autowired
    private VendedorRepository Repository;

    public List<Vendedor> listartudo() {
        return Repository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Vendedor cadastrar(Vendedor cadastrar) {
        return this.Repository.save(cadastrar);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Vendedor atualizar) {
        final Vendedor marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id), "Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()), "nao identificado o registro informado");
        final LocalDateTime saida =  LocalDateTime.now();
        atualizar.setEdicao(saida);
        this.Repository.save(atualizar);
    }
}
