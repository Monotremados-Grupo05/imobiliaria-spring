package br.com.uniamerica.imobiliaria.Controllers;

import br.com.uniamerica.imobiliaria.Entity.Ask;
import br.com.uniamerica.imobiliaria.Service.AskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.uniamerica.imobiliaria.Repository.AskRepository;
@Controller
@RequestMapping(value = "/api/ask")
public class AskController {
    @Autowired
    private AskRepository repository;

    @Autowired
    private AskService Servise;

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listarId(@PathVariable(value = "id")Long id){
        Ask listarid = repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body("Erro valor nao encontrado")
                : ResponseEntity.ok(listarid);
    }


    @PostMapping("/cadastrar")
    public  ResponseEntity<?> cadastrar(@RequestBody Ask cadastro){
        try{
            this.Servise.cadastrar(cadastro);
            return ResponseEntity.ok("Casdastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(" ja ixiste ");
        } catch ( Exception e){
            return ResponseEntity.badRequest().body("Erro:" + e.getStackTrace());
        }


    }








}





