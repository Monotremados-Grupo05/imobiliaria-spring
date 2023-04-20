package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Building", schema = "public")
public class Building {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private long id;
    @Getter
    @Setter
    @Column(name="rooms",nullable=false)
    private int rooms;
    @Getter
    @Setter
    @Column(name = "square_meters",nullable = false)
    private double square_meters;

    @Getter
    @Setter
    @Column(name = "suites",nullable = false)
    private int suites;
    @Getter
    @Setter
    @Column(name = "bathrooms",nullable = false)
    private int bathrooms;
}