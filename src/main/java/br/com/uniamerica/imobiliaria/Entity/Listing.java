package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Listing", schema = "public")
public class Listing {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private long id;


    @Getter
    @Setter
    @Column(name = "photoURLs",nullable = false,length = 100)
    private String photoURLs;

}