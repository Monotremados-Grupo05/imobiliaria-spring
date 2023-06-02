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
@RequestMapping(value = "/api/vendedor")
public class VendedorController {
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PessoaRepository pessoaRepository;

    ///////////////////////////////////GET ID VENDEDOR///////////////////////////////////
    @GetMapping
    public ResponseEntity<?> idVendedor(@RequestParam("id") final Long id) {
        try {
            return ResponseEntity.ok(pessoaService.procurarPessoa(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    ///////////////////////////////////GET LISTA VENDEDOR///////////////////////////////////
    @GetMapping({"/lista"})
    public ResponseEntity<?> ListaVendedor() {
        return ResponseEntity.ok(pessoaService.listaPessoa());
    }

    ///////////////////////////////////GET ATIVOS VENDEDOR///////////////////////////////////
    @GetMapping({"/ativos"})
    public ResponseEntity<?> getAtivos() {
        return ResponseEntity.ok(pessoaService.ativosPessoa());
    }
///////////////////////////////////POST PESSOA///////////////////////////////////

    @PostMapping
    public ResponseEntity<?> cadastrarVendedor(@RequestBody final Pessoa pessoa) {
        try {
            this.pessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.ok("Pessoa cadastrada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
    ///////////////////////////////////PUT VENDEDOR///////////////////////////////////
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarVendedor(@PathVariable final @NotNull Long id, @RequestBody final Pessoa pessoa) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            Pessoa pessoaAtualizado = pessoaExistente.get();
            pessoaService.atualizarPessoa(pessoaAtualizado.getId(), pessoa);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }
    ///////////////////////////////////DELETE VENDEDOR///////////////////////////////////
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVendedor(@PathVariable final Long id) {
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
