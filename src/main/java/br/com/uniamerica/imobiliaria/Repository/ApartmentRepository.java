package br.com.uniamerica.imobiliaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.uniamerica.imobiliaria.Entity.Apartment;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}

