package br.com.uniamerica.imobiliaria.Service;

import br.com.uniamerica.imobiliaria.Entity.Comprador;
import br.com.uniamerica.imobiliaria.Repository.CompradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        } else{
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
