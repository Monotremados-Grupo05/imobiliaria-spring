package br.com.uniamerica.imobiliaria.Controllers;

import br.com.uniamerica.imobiliaria.Entity.Descricao;
import br.com.uniamerica.imobiliaria.Entity.Operacao;
import br.com.uniamerica.imobiliaria.Repository.DescricaoRepository;
import br.com.uniamerica.imobiliaria.Service.DescricaoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/descricao")
public class DescricaoController {
    @Autowired
    private DescricaoService descricaoService;
    @Autowired
    private DescricaoRepository descricaoRepository;
///////////////////////////////////GET ID DESCRICAO///////////////////////////////////
    @GetMapping
    public ResponseEntity<?> idDescricao(@RequestParam("id") final Long id) {
        try {
            return ResponseEntity.ok(descricaoService.procurarDescricao(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
///////////////////////////////////GET LISTA DESCRICAO///////////////////////////////////
    @GetMapping({"/lista"})
    public ResponseEntity<?> ListaDescricao() {
        return ResponseEntity.ok(descricaoService.listaDescricao());
    }

///////////////////////////////////GET ATIVOS DESCRICAO///////////////////////////////////
    @GetMapping({"/ativos"})
    public ResponseEntity<?> getAtivos() {
        return ResponseEntity.ok(descricaoService.ativosDescricao());
    }
    ///////////////////////////////////POST DESCRICAO///////////////////////////////////
    @PostMapping
    public ResponseEntity<?> cadastrarDescricao(@RequestBody final Descricao descricao) {
        try {
            this.descricaoService.cadastrarDescricao(descricao);
            return ResponseEntity.ok("Operacao cadastrada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
    ///////////////////////////////////PUT DESCRICAO///////////////////////////////////
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDescricao(@PathVariable final @NotNull Long id, @RequestBody final Descricao descricao) {
        Optional<Descricao> descricaoExistente = descricaoRepository.findById(id);
        if (descricaoExistente.isPresent()) {
            Descricao descricaoAtualizada = descricaoExistente.get();
            descricaoService.atualizarDescricao(descricaoAtualizada.getId(), descricao);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }
    ///////////////////////////////////DELETE DESCRICAO///////////////////////////////////
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarDescricao(@PathVariable final Long id) {
        Optional<Descricao> optionalDescricao = descricaoRepository.findById(id);

        if (optionalDescricao.isPresent()) {
            Descricao descricao = optionalDescricao.get();

            if (descricao.isAtivo()) {
                descricaoRepository.delete(descricao);
                return ResponseEntity.ok().body("O registro da operacao foi deletada com sucesso");
            } else {
                descricao.setAtivo(false);
                descricaoRepository.save(descricao);
                return ResponseEntity.ok().body("A operacao estava vinculado a uma ou mais movimentações e foi desativado com sucesso");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

