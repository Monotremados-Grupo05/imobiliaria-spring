package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "Price", schema = "public")
public class Price extends Ask{
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private long id;
    @Getter
    @Setter
    @Column(name="asks",nullable=false)
    private Double asks = calculo_asks( getAmount(), getDiscount());
    @Getter
    @Setter
    @Column(name = "time",nullable = false)
    private LocalTime time;

    public static Double calculo_asks(double amount,double discount){
         double asks = amount * (1 - discount/100);
        return asks;
    }
}