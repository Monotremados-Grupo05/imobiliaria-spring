package br.com.uniamerica.imobiliaria.Repository;

import br.com.uniamerica.imobiliaria.Entity.Proprietario;
import br.com.uniamerica.imobiliaria.Entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario,Long> {
    List<Proprietario> findByAtivo(boolean ativo);

}
