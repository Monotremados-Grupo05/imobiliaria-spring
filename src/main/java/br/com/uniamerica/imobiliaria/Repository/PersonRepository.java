package br.com.uniamerica.imobiliaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.uniamerica.imobiliaria.Entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
