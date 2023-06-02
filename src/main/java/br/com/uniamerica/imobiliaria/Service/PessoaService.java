package br.com.uniamerica.imobiliaria.Service;

import br.com.uniamerica.imobiliaria.Entity.Pessoa;
import br.com.uniamerica.imobiliaria.Repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

///////////////////////////////////GET ID PESSOA///////////////////////////////////
    public Optional<Pessoa> procurarPessoa(Long id){
        if (!pessoaRepository.ProcuraId(id) ){
            throw new RuntimeException("Esse ID nao esta no banco de dados, verifique e tente novamente");
        }else {
            Optional<Pessoa> pessoa = this.pessoaRepository.findById(id);
            return pessoa;
        }
    }
    ///////////////////////////////////GET LISTA PESSOA///////////////////////////////////
    public List<Pessoa> listaPessoa(){
        List<Pessoa> pessoa = pessoaRepository.findAll();
        return pessoa;
    }
    ///////////////////////////////////GET ATIVOS PESSOA///////////////////////////////////
    public List<Pessoa> ativosPessoa(){
        List<Pessoa> pessoa = pessoaRepository.findByAtivo(true);
        return pessoa;
    }
    ///////////////////////////////////POST PESSOA///////////////////////////////////
    @Transactional(rollbackOn = Exception.class)
    public void cadastrarPessoa(final Pessoa pessoa){
        if(pessoa.getEmail()==null){
            throw new RuntimeException("Email nulo, verifique e tente novamente");
        }else if(pessoa.getNome() == null){
            throw new RuntimeException("Nome nulo, verifique e tente novamente");
        }else if(pessoa.getCpfCnpj() == null){
            throw new RuntimeException("CPF/CNPJ nulo, verifique e tente novamente");
        }else if(pessoa.getPhone() == null){
            throw new RuntimeException("Telefone nulo, verifique e tente novamente");
        }
        else{
            pessoaRepository.save(pessoa);
        }
    }
    ///////////////////////////////////PUT PESSOA///////////////////////////////////
    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizado) {
        Pessoa pessoaExistente = pessoaRepository.findById(id).orElse(null);
        if (pessoaExistente == null) {
            return null;
        } else {

            pessoaExistente.setEmail(pessoaAtualizado.getEmail());
            pessoaExistente.setNome(pessoaAtualizado.getNome());
            pessoaExistente.setCpfCnpj(pessoaAtualizado.getCpfCnpj());
            pessoaExistente.setPhone(pessoaAtualizado.getPhone());
            return pessoaRepository.save(pessoaExistente);
        }

    }
}

