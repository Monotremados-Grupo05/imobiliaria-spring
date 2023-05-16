package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "proprietario",schema = "public")
@Audited
@AuditTable(value = "proprietario_audit", schema = "audit")
public class Proprietario extends Pessoa {
    @Setter
    @Getter
    @Column(name="documento",nullable=false)
    private String documento;
}
