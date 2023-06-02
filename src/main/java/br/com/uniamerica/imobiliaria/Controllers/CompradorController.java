package br.com.uniamerica.imobiliaria.Controllers;

import br.com.uniamerica.imobiliaria.Entity.Comprador;
import br.com.uniamerica.imobiliaria.Repository.CompradorRepository;
import br.com.uniamerica.imobiliaria.Service.CompradorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping (value = "/api/comprador")
public class CompradorController {
    @Autowired
    private CompradorService compradorService;
    @Autowired
    private CompradorRepository compradorRepository;

    ///////////////////////////////////GET ID COMPRADORES///////////////////////////////////
    @GetMapping
    public ResponseEntity<?> idComprador(@RequestParam("id") final Long id) {
        try {
            return ResponseEntity.ok(compradorService.procurarComprador(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

///////////////////////////////////GET LISTA COMPRADORES///////////////////////////////////
    @GetMapping({"/lista"})
    public ResponseEntity<?> ListaComprador() {
        return ResponseEntity.ok(compradorService.listaComprador());
    }

    ///////////////////////////////////GET ATIVOS COMPRADORES///////////////////////////////////
    @GetMapping({"/ativos"})
    public ResponseEntity<?> getAtivos() {
        return ResponseEntity.ok(compradorService.ativosComprador());
    }
///////////////////////////////////POST COMPRADORES///////////////////////////////////

    @PostMapping
    public ResponseEntity<?> cadastrarCompador(@RequestBody final Comprador comprador) {
        try {
            this.compradorService.cadastrarComprador(comprador);
            return ResponseEntity.ok("Comprador cadastrado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
///////////////////////////////////PUT COMPRADORES///////////////////////////////////
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarComprador(@PathVariable final @NotNull Long id, @RequestBody final Comprador comprador) {
        Optional<Comprador> compradorExistente = compradorRepository.findById(id);
        if (compradorExistente.isPresent()) {
            Comprador compradorAtualizado = compradorExistente.get();
            compradorService.atualizarComprador(compradorAtualizado.getId(), comprador);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado");
        }
    }
///////////////////////////////////DELETE COMPRADORES///////////////////////////////////
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarComprador(@PathVariable final Long id) {
        Optional<Comprador> optionalComprador = compradorRepository.findById(id);

        if (optionalComprador.isPresent()) {
            Comprador comprador = optionalComprador.get();

            if (comprador.isAtivo()) {
                compradorRepository.delete(comprador);
                return ResponseEntity.ok().body("O registro do comprador foi deletado com sucesso");
            } else {
                comprador.setAtivo(false);
                compradorRepository.save(comprador);
                return ResponseEntity.ok().body("O comprador estava vinculado a uma ou mais movimentações e foi desativado com sucesso");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
}


}







