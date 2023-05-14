package br.com.uniamerica.imobiliaria.Controllers;

import br.com.uniamerica.imobiliaria.Entity.Person;
import br.com.uniamerica.imobiliaria.Entity.Seller;
import br.com.uniamerica.imobiliaria.Repository.SellerRepository;
import br.com.uniamerica.imobiliaria.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/seller")
public class SellerController {
    @Autowired
    private SellerRepository repository;
    @Autowired
    private SellerService service;

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listarId(@PathVariable(value = "id")Long id){
        Person listarid = repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body("Erro valor nao encontrado")
                : ResponseEntity.ok(listarid);
    }

    @PostMapping("/cadastrar")
    public  ResponseEntity<?> cadastrar(@RequestBody Seller cadastro) {
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
