package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public  abstract class Property {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private long id;
    @Getter @Setter
    @Column(name="nome",nullable=false,length=100)
    private String name;
    @Getter @Setter
    @Column(name="dimensions",nullable=false)
    public double dimensions;
    @Getter @Setter
    @Column(name="Value",nullable=false)
    public BigDecimal Value;
}
