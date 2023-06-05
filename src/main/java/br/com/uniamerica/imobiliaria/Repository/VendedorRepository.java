package br.com.uniamerica.imobiliaria.Repository;

import br.com.uniamerica.imobiliaria.Entity.Propriedade;
import br.com.uniamerica.imobiliaria.Entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VendedorRepository extends JpaRepository<Vendedor,Long> {
    List<Vendedor> findByAtivo(boolean ativo);
}
