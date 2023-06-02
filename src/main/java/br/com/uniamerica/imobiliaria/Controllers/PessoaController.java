package br.com.uniamerica.imobiliaria.Controllers;

import br.com.uniamerica.imobiliaria.Entity.Pessoa;
import br.com.uniamerica.imobiliaria.Repository.PessoaRepository;
import br.com.uniamerica.imobiliaria.Service.PessoaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PessoaRepository pessoaRepository;

    ///////////////////////////////////GET ID PESSOA///////////////////////////////////
    @GetMapping
    public ResponseEntity<?> idPessoa(@RequestParam("id") final Long id) {
        try {
            return ResponseEntity.ok(pessoaService.procurarPessoa(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    ///////////////////////////////////GET LISTA PESSOA///////////////////////////////////
    @GetMapping({"/lista"})
    public ResponseEntity<?> ListaPessoa() {
        return ResponseEntity.ok(pessoaService.listaPessoa());
    }

    ///////////////////////////////////GET ATIVOS PESSOA///////////////////////////////////
    @GetMapping({"/ativos"})
    public ResponseEntity<?> getAtivos() {
        return ResponseEntity.ok(pessoaService.ativosPessoa());
    }
///////////////////////////////////POST PESSOA///////////////////////////////////

    @PostMapping
    public ResponseEntity<?> cadastrarCompador(@RequestBody final Pessoa pessoa) {
        try {
            this.pessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.ok("Pessoa cadastrada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
    ///////////////////////////////////PUT PESSOA///////////////////////////////////
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable final @NotNull Long id, @RequestBody final Pessoa pessoa) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            Pessoa pessoaAtualizado = pessoaExistente.get();
            pessoaService.atualizarPessoa(pessoaAtualizado.getId(), pessoa);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }
    ///////////////////////////////////DELETE PESSOA///////////////////////////////////
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable final Long id) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);

        if (optionalPessoa.isPresent()) {
            Pessoa pessoa = optionalPessoa.get();

            if (pessoa.isAtivo()) {
                pessoaRepository.delete(pessoa);
                return ResponseEntity.ok().body("O registro do pessoa foi deletado com sucesso");
            } else {
                pessoa.setAtivo(false);
                pessoaRepository.save(pessoa);
                return ResponseEntity.ok().body("O pessoa estava vinculado a uma ou mais movimentações e foi desativado com sucesso");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}