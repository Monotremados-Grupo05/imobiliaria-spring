package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Persons",schema ="public" )
public class Person {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private Long id;

    @Getter
    @Setter
    @Column(name="email",nullable=false,length=100)
    private String email;
    @Getter
    @Setter
    @Column(name="name",nullable=false,length=100)
    private String name;
    @Getter
    @Setter
    @Column(name="phone",nullable=false,length=10,unique = true)
    private String phone;
}
