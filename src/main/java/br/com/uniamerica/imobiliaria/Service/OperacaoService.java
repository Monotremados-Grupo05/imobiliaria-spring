package br.com.uniamerica.imobiliaria.Service;

import br.com.uniamerica.imobiliaria.Entity.Operacao;
import br.com.uniamerica.imobiliaria.Repository.OperacaoRepository;
import jakarta.transaction.Transactional;
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
    public List<Operacao> ativosOperacao(){
        List<Operacao> operacao = operacaoRepository.findByAtivo(true);
        return operacao;
    }
///////////////////////////////////POST OPERACAO///////////////////////////////////
    @Transactional(rollbackOn = Exception.class)
    public void cadastrarOperacao(final Operacao operacao){
        if(operacao.getContrato()==null){
            throw new RuntimeException("Contrato nulo, verifique e tente novamente");
        }else if(operacao.getComprador() == null){
            throw new RuntimeException("Comprador nulo, verifique e tente novamente");
        }else if(operacao.getProprietario()==null){
            throw new RuntimeException("Proprietario nulo, verifique e tente novamente");
        }else if(operacao.getVendedor()==null){
            throw new RuntimeException("Vendedor nulo, verifique e tente novamente");
        }else if(operacao.getContratoVenda()==null){
            throw new RuntimeException("Cotrato da venda nulo, verifique e tente novamente");
        }else if(operacao.getDescricaoVenda()==null){
            throw new RuntimeException("Descricao nula, verifique e tente novamente");
        }else if(operacao.getPropriedade()==null){
            throw new RuntimeException("Propriedade nulo, verifique e tente novamente");
        }else if(operacao.getValor()==null){
            throw new RuntimeException("Valor nulo, verifique e tente novamente");
        }
        else{
            operacaoRepository.save(operacao);
        }
    }
///////////////////////////////////PUT OPERACAO///////////////////////////////////
    public Operacao atualizarOperacao(Long id, Operacao operacaoAtualizado) {
        Operacao operacaoExistente = operacaoRepository.findById(id).orElse(null);
        if (operacaoExistente == null) {
            return null;
        } else {
            operacaoExistente.setContrato(operacaoAtualizado.getContrato());
            operacaoExistente.setComprador(operacaoAtualizado.getComprador());
            operacaoExistente.setProprietario(operacaoAtualizado.getProprietario());
            operacaoExistente.setVendedor(operacaoAtualizado.getVendedor());
            operacaoExistente.setContrato(operacaoAtualizado.getContrato());
            operacaoExistente.setContratoVenda(operacaoAtualizado.getContratoVenda());
            operacaoExistente.setDescricaoVenda(operacaoAtualizado.getDescricaoVenda());
            operacaoExistente.setPropriedade(operacaoAtualizado.getPropriedade());
            operacaoExistente.setValor(operacaoAtualizado.getValor());
            return operacaoRepository.save(operacaoExistente);
        }

    }
}
