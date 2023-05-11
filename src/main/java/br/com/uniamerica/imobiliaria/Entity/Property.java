package br.com.uniamerica.imobiliaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Table(name = "Propertys", schema = "public")
@MappedSuperclass
public  abstract class Property {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true)
    private long id;

    @Getter @Setter
    @Column(name="nome",nullable=false,length=100)
    private String name;
    @Getter
    @Setter
    @Column(name="postDescription",nullable=false,length = 200)
    private String postDescription;

    @Getter @Setter
    @Column(name="posttitle",nullable=false,length = 100)
    private String posttitle;

    @Getter @Setter
    @Column(name="status",nullable=false)
    private LisitingStatus status;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="sellagent",nullable=false)
    private Seller sellagent;

    @Getter @Setter
    @Column(name="lastupdate",nullable=false)
    private LocalDateTime lastupdate;

    @Getter @Setter
    @Column(name="dtCadastro",nullable=false)
    private LocalDateTime cadastro;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="propertyowner",nullable=false)
    private Owner propertyowner;

    @Getter @Setter
    @OneToOne
    @JoinColumn(name="location",nullable=false)
    private Location location;

    @Getter @Setter
    @Column(name="dimensions_length",nullable=false)
    private Double dimensions_length;

    @Getter @Setter
    @Column(name="dimensions_width",nullable=false)
    private Double dimensions_width;

    @Getter @Setter
    @Column(name="value",nullable=false,length = 100)
    private String value;

    @Getter @Setter
    @Column(name="tipe",nullable=false)
    private String tipe;


    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "property",
            uniqueConstraints = @UniqueConstraint(
                    columnNames ={
                            "property_id",
                            "listing_id"
                    }
            ), joinColumns = @JoinColumn(
            name = "property_id"
    ), inverseJoinColumns = @JoinColumn(
            name = "listing_id"
    )
    )
    private List<Listing> listing;


}


