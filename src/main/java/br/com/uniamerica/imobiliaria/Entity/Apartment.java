package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Apartment", schema = "public")
public class Apartment {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private long id;
    @Getter
    @Setter
    @Column(name="floor",nullable=false)
    private int floor;
    @Getter
    @Setter
    @Column(name = "name",nullable = false)

    private String name;
    @Getter
    @Setter
    @Column(name = "price",nullable = false)
    private double price;

    @Getter
    @Setter
    @Column(name = "interphone",nullable = false)
    private int interphone;
    @Getter
    @Setter
    @Column(name = "parkings",nullable = false)
    private int parkings;
}