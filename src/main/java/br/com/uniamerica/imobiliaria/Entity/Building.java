package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Building extends Property{
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private long id;
    @Getter
    @Setter
    @Column(name="rooms")
    private int rooms;
    @Getter
    @Setter
    @Column(name = "square_meters")
    private double square_meters;

    @Getter
    @Setter
    @Column(name = "suites")
    private int suites;
    @Getter
    @Setter
    @Column(name = "bathrooms")
    private int bathrooms;
}