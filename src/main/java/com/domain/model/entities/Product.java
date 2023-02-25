package com.domain.model.entities;

import java.io.Serializable;
import java.util.Set;

// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_product")
// @JsonIdentityInfo( // mengatasi cycle, agar yang diambil cuma property 'id'nya saja, tidak berkelanjutan
//     generator = ObjectIdGenerators.PropertyGenerator.class,
//     property = "id"
// ) 
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        name = "product_name",
        length = 100
    )
    @NotEmpty(message = "Name is required")
    private String name;

    @Column(
        name = "product_description",
        length = 500
    )
    @NotEmpty(message = "Description is required")
    private String description;

    private double price;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(
        name = "tbl_product_supplier", // tabel perantara many to many
        joinColumns = @JoinColumn(name = "product_id"), // primary key tabel ini
        inverseJoinColumns = @JoinColumn(name = "supplier_id") // di tabel lain
    )
    @JsonManagedReference // biar dibatasi & tidak looping/cycling
    private Set<Supplier> suppliers;

}