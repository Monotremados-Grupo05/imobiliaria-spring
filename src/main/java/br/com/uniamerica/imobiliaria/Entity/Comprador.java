package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "comprador",schema = "public")
public class Comprador extends Pessoa {
    @Getter
    @Setter
    @Column(name="documento",nullable=false)
    private String documento;
}
