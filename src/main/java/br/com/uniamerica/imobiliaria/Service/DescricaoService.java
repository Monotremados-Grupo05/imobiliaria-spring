package br.com.uniamerica.imobiliaria.Service;

import br.com.uniamerica.imobiliaria.Entity.Descricao;
import br.com.uniamerica.imobiliaria.Entity.Operacao;
import br.com.uniamerica.imobiliaria.Repository.DescricaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DescricaoService {
    @Autowired
    private DescricaoRepository descricaoRepository;
///////////////////////////////////GET ID DESCRICAO///////////////////////////////////
    public Optional<Descricao> procurarDescricao(Long id){
        if (!descricaoRepository.ProcuraId(id) ){
            throw new RuntimeException("Esse ID nao esta no banco de dados, verifique e tente novamente");
        }else {
            Optional<Descricao> descricao = this.descricaoRepository.findById(id);
            return descricao;
        }
    }
    ///////////////////////////////////GET LISTA DESCRICAO///////////////////////////////////
    public List<Descricao> listaDescricao(){
        List<Descricao> descricao = descricaoRepository.findAll();
        return descricao;
    }
    ///////////////////////////////////GET ATIVOS DESCRICAO///////////////////////////////////
    public List<Descricao> ativosDescricao(){
        List<Descricao> descricao = descricaoRepository.findByAtivo(true);
        return descricao;
    }
    ///////////////////////////////////POST DESCRICAO///////////////////////////////////
    @Transactional(rollbackOn = Exception.class)
    public void cadastrarDescricao(final Descricao descricao){
        if(descricao.getDescricao()==null){
            throw new RuntimeException("Contrato nulo, verifique e tente novamente");
        }else if(descricao.getId() == null){
            throw new RuntimeException("Comprador nulo, verifique e tente novamente");
        }
        else{
            descricaoRepository.save(descricao);
        }
    }
    ///////////////////////////////////PUT DESCRICAO///////////////////////////////////
    public Descricao atualizarDescricao(Long id, Descricao descricaoAtualizada) {
        Descricao descricaoExistente = descricaoRepository.findById(id).orElse(null);
        if (descricaoExistente == null) {
            return null;
        } else {
            descricaoExistente.setDescricao(descricaoAtualizada.getDescricao());
            descricaoExistente.setId(descricaoAtualizada.getId());
            return descricaoRepository.save(descricaoExistente);
        }

    }
}
