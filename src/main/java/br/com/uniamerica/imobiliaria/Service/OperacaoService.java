package br.com.uniamerica.imobiliaria.Service;

import br.com.uniamerica.imobiliaria.Entity.Operacao;
import br.com.uniamerica.imobiliaria.Repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperacaoService {
    @Autowired

    private OperacaoRepository operacaoRepository;
///////////////////////////////////GET ID OPERACAO///////////////////////////////////
    public Optional<Operacao> procurarOperacao(Long id){
        if (!operacaoRepository.ProcuraId(id) ){
            throw new RuntimeException("Esse ID nao esta no banco de dados, verifique e tente novamente");
        }else {
            Optional<Operacao> operacao = this.operacaoRepository.findById(id);
            return operacao;
        }
    }
///////////////////////////////////GET LISTA OPERACAO///////////////////////////////////
    public List<Operacao> listaOperacao(){
        List<Operacao> operacao = operacaoRepository.findAll();
        return operacao;
    }
///////////////////////////////////GET ATIVOS OPERACAO///////////////////////////////////
  /*  public List<Operacao> ativosOperacao(){
        List<Operacao> operacao = operacaoRepository.findByAtivo(true);
        return operacao;
    }
///////////////////////////////////POST OPERACAO///////////////////////////////////
    @Transactional(rollbackOn = Exception.class)
    public void cadastrarOperacao(final Operacao operacao){
        if(operacao.getDocumento() == null){
            throw new RuntimeException("O Documento nao pode ser nulo, verifique e tente novamente");
        } else{
            operacaoRepository.save(operacao);
        }
    }
///////////////////////////////////PUT OPERACAO///////////////////////////////////
    public Operacao atualizarOperacao(Long id, Operacao operacaoAtualizado) {
        Operacao operacaoExistente = operacaoRepository.findById(id).orElse(null);
        if (operacaoExistente == null) {
            return null;
        } else {
            operacaoExistente.setDocumento(operacaoAtualizado.getDocumento());
            return operacaoRepository.save(operacaoExistente);
        }

    }*/
}
