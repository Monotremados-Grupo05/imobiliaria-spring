package br.com.uniamerica.imobiliaria.Controllers;

import br.com.uniamerica.imobiliaria.Entity.Admin;
import br.com.uniamerica.imobiliaria.Repository.AdminRepository;
import br.com.uniamerica.imobiliaria.Servise.AdminServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private AdminServise Servise;

    @Autowired
    private AdminRepository Repository;


    //public ResponseEntity<List<Admin>> lista(){return  ResponseEntity.ok(this.Servise.listaCompleta())();}
    @GetMapping("/lista/id/{id}")
    public  ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Admin listarid = Repository.findById(id).orElse(null);
        return listarid ==null
                ? ResponseEntity.badRequest().body("Erro: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadatrar(@RequestBody Admin admin){
        try{
            this.Servise.cadastrar(admin);
            return ResponseEntity.ok("Cadastro bem sucedido");
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Esse adm ja exixte");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro"+ e.getStackTrace());
        }
    }






}
