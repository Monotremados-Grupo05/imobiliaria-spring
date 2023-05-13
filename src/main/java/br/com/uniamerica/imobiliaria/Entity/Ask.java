package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "Asks", schema = "public")
public class Ask {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
   private Long id;
    @Getter
    @Setter
    @Column(name="currency",nullable=false,length=100)
    private String currency;
    @Getter
    @Setter
    @Column(name = "amount",nullable = false)
    private double amount;
    @Getter
    @Setter
    @Column(name = "discount",nullable = false)
    private double discount;
    @Getter
    @Setter
    @Column(name = "time",nullable = false)
    private LocalTime time;


}
