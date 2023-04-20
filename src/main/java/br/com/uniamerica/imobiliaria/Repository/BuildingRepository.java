package br.com.uniamerica.imobiliaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.uniamerica.imobiliaria.Entity.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}