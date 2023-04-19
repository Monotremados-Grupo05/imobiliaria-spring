package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass

public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique = true)
    private long id;
    @Getter @Setter

    @JoinTable(name="Operation_for_Ask",
            uniqueConstraints = @UniqueConstraint(columnNames = {"Operation_id","Ask_id"}),
            joinColumns = @JoinColumn( name = "Operation_id"),
            inverseJoinColumns = @JoinColumn(name= "Ask_id"))

    @OneToMany
    private Ask price;
    @Getter
    @Setter
    @Column(name = "property",nullable = false)
    private Property property;
    @Getter
    @Setter
    @Column(name = "PreviousOwner",nullable = false)
    private Owner PreviousOwner;
    @Getter
    @Setter
    @Column(name = "NewOwner",nullable = false)
    private Buyer NewOwner;
    @Getter
    @Setter
    @Column(name = "sellAgent",nullable = false)
    private Seller sellAgent;
    @Getter
    @Setter
    @Column(name = "operationDate",nullable = false)

    private LocalDateTime operationDate;
    @Getter
    @Setter
    @Column(name = "contract",nullable = false)

    private Contract contract;



}
