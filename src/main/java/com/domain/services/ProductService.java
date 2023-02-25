package com.domain.services;

import java.util.List;

import com.domain.model.entities.Product;
import com.domain.model.entities.Supplier;

public interface ProductService {

    Product save(Product product);

    Product findOne(Long id);

    Iterable<Product> findAll();

    void removeOne(Long id);

    List<Product> findByName(String name);

    void addSupplier(Supplier supplier, Long productId);

    Product findProductByName(String name);

    List<Product> findProductByNameLike(String name);

    List<Product> findByCategory(Long categoryId);

    List<Product> findBySupplier(Long supplierId);

}
