package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "propriedade",schema = "public")
@Audited
@AuditTable(value = "propriedade_audit", schema = "audit")
public class Propriedade extends AbstractEntity{
    @Getter @Setter
    @OneToOne
    @JoinColumn(name="proprietario",nullable=false)
    private Proprietario proprietario;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="vendedor",nullable=false)
    private Vendedor vendedor;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="localizacao",nullable=false)
    private Localizacao localizacao;
    @Getter @Setter
    @Column(name="largura",nullable=false)
    private Double largura;
    @Getter @Setter
    @Column(name="comprimento",nullable=false)
    private Double comprimento;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="descricao",nullable=false)
    private Descricao descricao;
    @Getter @Setter
    @Column(name="staus",nullable=false)
    private Status staus;
    @Getter @Setter
    @Column(name="titulo",nullable=false)
    private String titulo;
    @Getter @Setter
    @OneToMany
    @JoinColumn(name="tipo",nullable=false)
    private List<Construcao> tipo;
}


