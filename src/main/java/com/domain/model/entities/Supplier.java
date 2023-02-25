package com.domain.model.entities;

import java.io.Serializable;
import java.util.Set;

// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_suppliers")
// @JsonIdentityInfo( // mengatasi cycle, agar yang diambil cuma property 'id'nya saja, tidak berkelanjutan
//     generator = ObjectIdGenerators.PropertyGenerator.class,
//     property = "id"
// ) 
public class Supplier implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        length = 150,
        nullable = false
    )
    private String name;

    @Column(
        length = 200,
        nullable = false
    )
    private String address;

    @Column(
        length = 100,
        nullable = false,
        unique = true
    )
    private String email;

    @ManyToMany(mappedBy = "suppliers")
    @JsonBackReference
    private Set<Product> products;

}