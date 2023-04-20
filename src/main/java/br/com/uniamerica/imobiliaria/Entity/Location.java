package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "Locations", schema = "public")
public class Location {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private long id;
    @Getter @Setter
    @Column(name="cep",nullable=false,length = 100)
    private String cep;
    @Getter @Setter
    @Column(name="numero",nullable=false,length = 100)
    private String numero;


}
