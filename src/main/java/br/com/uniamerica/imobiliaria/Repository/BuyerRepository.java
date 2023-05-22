package br.com.uniamerica.imobiliaria.Repository;

import br.com.uniamerica.imobiliaria.Entity.Building;
import br.com.uniamerica.imobiliaria.Entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    public void desativar(@Param("idBuyer")Long id);
}