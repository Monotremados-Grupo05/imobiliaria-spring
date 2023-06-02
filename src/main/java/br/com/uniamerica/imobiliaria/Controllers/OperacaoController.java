package br.com.uniamerica.imobiliaria.Controllers;

import br.com.uniamerica.imobiliaria.Entity.Operacao;
import br.com.uniamerica.imobiliaria.Repository.OperationRepository;
import br.com.uniamerica.imobiliaria.Service.OperacaoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/operacao")
public class OperacaoController {
    @Autowired
    private OperacaoService operacaoService;
    @Autowired
    private OperationRepository operationRepository;

    ///////////////////////////////////GET ID OPERACAO///////////////////////////////////
    @GetMapping
    public ResponseEntity<?> idOperacao(@RequestParam("id") final Long id) {
        try {
            return ResponseEntity.ok(operacaoService.procurarOperacao(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
///////////////////////////////////GET LISTA OPERACAO///////////////////////////////////
    @GetMapping({"/lista"})
    public ResponseEntity<?> ListaOperacoes() {
        return ResponseEntity.ok(operacaoService.listaOperacao());
    }

///////////////////////////////////GET ATIVOS OPERACAO///////////////////////////////////
    @GetMapping({"/ativos"})
    public ResponseEntity<?> getAtivos() {
        return ResponseEntity.ok(operacaoService.ativosOperacao());
    }
///////////////////////////////////POST OPERACAO///////////////////////////////////
    @PostMapping
    public ResponseEntity<?> cadastrarOperacao(@RequestBody final Operacao operacao) {
        try {
            this.operacaoService.cadastrarOperacao(operacao);
            return ResponseEntity.ok("Operacao cadastrada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
///////////////////////////////////PUT OPERACAO///////////////////////////////////
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarOperacao(@PathVariable final @NotNull Long id, @RequestBody final Operacao operacao) {
        Optional<Operacao> operacaoExistente = operationRepository.findById(id);
        if (operacaoExistente.isPresent()) {
            Operacao operacaoAtualizada = operacaoExistente.get();
            operacaoService.atualizarOperacao(operacaoAtualizada.getId(), operacao);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }
///////////////////////////////////DELETE OPERACAO///////////////////////////////////
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarOperacao(@PathVariable final Long id) {
        Optional<Operacao> optionalOperacao = operationRepository.findById(id);

        if (optionalOperacao.isPresent()) {
            Operacao operacao = optionalOperacao.get();

            if (operacao.isAtivo()) {
                operationRepository.delete(operacao);
                return ResponseEntity.ok().body("O registro do comprador foi deletado com sucesso");
            } else {
                operacao.setAtivo(false);
                operationRepository.save(operacao);
                return ResponseEntity.ok().body("O comprador estava vinculado a uma ou mais movimentações e foi desativado com sucesso");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
