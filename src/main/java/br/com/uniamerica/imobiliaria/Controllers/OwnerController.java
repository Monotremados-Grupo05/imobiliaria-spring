package br.com.uniamerica.imobiliaria.Controllers;

import br.com.uniamerica.imobiliaria.Entity.Owner;
import br.com.uniamerica.imobiliaria.Repository.OwnerRepository;
import br.com.uniamerica.imobiliaria.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class OwnerController {
    @Autowired
    private OwnerRepository repository;
    @Autowired
    private OwnerService service;

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listarId(@PathVariable(value = "id")Long id){
        Owner listarid = repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body("Erro valor nao encontrado")
                : ResponseEntity.ok(listarid);
    }

    @PostMapping("/cadastrar")
    public  ResponseEntity<?> cadastrar(@RequestBody Owner cadastro) {
        try {
            this.service.cadastrar(cadastro);
            return ResponseEntity.ok("Casdastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(" ja ixiste ");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro:" + e.getStackTrace());
        }

    }

}

