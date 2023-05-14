package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Apartment extends Building{
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private long id;
    @Getter
    @Setter
    @Column(name="floor")
    private int floor;
    @Getter
    @Setter
    @Column(name = "name",length=30)
    private String name;
    @Getter
    @Setter
    @Column(name = "price",nullable = false)
    private double price;

    @Getter
    @Setter
    @Column(name = "interphone")
    private int interphone;
    @Getter
    @Setter
    @Column(name = "parkings")
    private int parkings;
}
