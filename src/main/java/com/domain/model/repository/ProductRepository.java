package com.domain.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.domain.model.entities.Product;
import com.domain.model.entities.Supplier;

import jakarta.websocket.server.PathParam;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameContains(String name);
    
    // JPA Custom Query
                                                    // parameter
    @Query("SELECT p FROM Product p WHERE p.name = :name") 
    Product findProductByName(@PathParam("name") String name);
    // notes:
    // bedanya JPAQl sama MySql: 
    // Kalo mysql 'FROM tabel' dan kolomnya memakai nama kolom
    // kalo JPAQl 'FROM kelas entity-nya' dan kolomnya juga memakai field yg ada di kelas entity-nya

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
    
}
