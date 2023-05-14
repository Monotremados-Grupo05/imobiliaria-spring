package br.com.uniamerica.imobiliaria.Controllers;

import br.com.uniamerica.imobiliaria.Entity.Apartment;
import br.com.uniamerica.imobiliaria.Repository.ApartmentRepository;
import br.com.uniamerica.imobiliaria.Service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService Service;

    @Autowired
    private ApartmentRepository Repository;

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id")Long id){
        Apartment listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body("Erro: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }

    @PutMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Apartment cadastro){
        try{
            this.Service.cadastrar(cadastro);
            return  ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("O Apartment ja exixte");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro" + e.getStackTrace());
        }


    }








}
