package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Operations", schema = "public")

public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique = true)
    private long id;

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "price",nullable = false)
    private Ask price;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "PreviousOwner",nullable = false)
    private Owner PreviousOwner;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "NewOwner",nullable = false)
    private Buyer NewOwner;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "sellAgent",nullable = false)
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
