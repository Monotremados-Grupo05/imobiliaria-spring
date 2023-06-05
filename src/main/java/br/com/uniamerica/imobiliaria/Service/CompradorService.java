package br.com.uniamerica.imobiliaria.Service;

import br.com.uniamerica.imobiliaria.Entity.Comprador;
import br.com.uniamerica.imobiliaria.Repository.CompradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompradorService {
    @Autowired
    private CompradorRepository compradorRepository;

///////////////////////////////////GET ID COMPRADORES///////////////////////////////////
    public Optional<Comprador> procurarComprador(Long id){
        if (!compradorRepository.ProcuraId(id) ){
            throw new RuntimeException("Esse ID nao esta no banco de dados, verifique e tente novamente");
        }else {
            Optional<Comprador> comprador = this.compradorRepository.findById(id);
            return comprador;
        }
    }
///////////////////////////////////GET LISTA COMPRADORES///////////////////////////////////
    public List<Comprador> listaComprador(){
        List<Comprador> comprador = compradorRepository.findAll();
        return comprador;
    }
///////////////////////////////////GET ATIVOS COMPRADORES///////////////////////////////////
    public List<Comprador> ativosComprador(){
    List<Comprador> comprador = compradorRepository.findByAtivo(true);
    return comprador;
    }
///////////////////////////////////POST COMPRADORES///////////////////////////////////

    @Transactional(rollbackOn = Exception.class)
    public void cadastrarComprador(final Comprador comprador){
        if(comprador.getDocumento() == null){
            throw new RuntimeException("O Documento nao pode ser nulo, verifique e tente novamente");
        }else if(comprador.getEmail()==null){
            throw new RuntimeException("Email nao pode ser nulo, verifique e tente novamente");
        }else if(comprador.getNome()==null){
            throw new RuntimeException("Nome nulo, verifique e tente novamente");
        }else if(comprador.getCpf()==null || comprador.getCnpj()==null){
            throw new RuntimeException("Insira um CPF ou um CNJP, verifique e tente novamente");
        }else if (!comprador.getCpf().matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}")|| !comprador.getCnpj().matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0001-][0-9]{2}")) {
            throw new RuntimeException("Insira um CPF ou um CNJP, verifique e tente novamente");
        }else if(comprador.getPhone()==null){
            throw new RuntimeException("Telefone nulo, verifique e tente novamente");
        }
        else{
            compradorRepository.save(comprador);
        }
    }
///////////////////////////////////PUT COMPRADORES///////////////////////////////////
    public Comprador atualizarComprador(Long id, Comprador compradorAtualizado) {
        Comprador compradorExistente = compradorRepository.findById(id).orElse(null);
        if (compradorExistente == null) {
            return null;
        } else {
            compradorExistente.setDocumento(compradorAtualizado.getDocumento());
            return compradorRepository.save(compradorExistente);
        }

    }








}
