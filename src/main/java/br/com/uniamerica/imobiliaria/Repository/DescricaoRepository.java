package br.com.uniamerica.imobiliaria.Repository;

import br.com.uniamerica.imobiliaria.Entity.Descricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DescricaoRepository extends JpaRepository<Descricao, Long> {
List<Descricao> findByAtivo(boolean ativo);

    @Query(value = "select exists (select * from descricao where id = :id)", nativeQuery = true)
    boolean ProcuraId(@Param("id") final Long id);
}

