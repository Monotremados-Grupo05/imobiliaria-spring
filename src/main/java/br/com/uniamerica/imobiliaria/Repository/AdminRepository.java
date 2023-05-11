package br.com.uniamerica.imobiliaria.Repository;

import br.com.uniamerica.imobiliaria.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {



}
