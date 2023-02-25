package com.domain.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.model.entities.Product;
import com.domain.model.entities.Supplier;
import com.domain.model.repos.ProductRepo;
import com.domain.services.ProductService;
import com.domain.services.SupplierService;

import jakarta.transaction.Transactional;

/**
 * Service
 * intinya, Service itu merupakan implementasi
 * dari bisnis logic kita
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    @Override
    public Product save(Product product) {
        return productRepo.save(product); // bisa buat update juga, jadi jpa secara pintar bisa membedakannya
    }

    @Override
    public Product findOne(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()) {
            return null;
        }
        return productRepo.findById(id).get();
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public void removeOne(Long id) {
        productRepo.deleteById(id);
        
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepo.findByNameContains(name);
    }

    @Override
    public void addSupplier(Supplier supplier, Long productId) {

        Product product = findOne(productId);
        if(product == null) {
            throw new RuntimeException("Product with ID: "+productId+" not found");
        }

        product.getSuppliers().add(supplier);
        save(product);
        
    }

    @Override
    public Product findProductByName(String name) {
        return productRepo.findProductByName(name);
    }

    @Override
    public List<Product> findProductByNameLike(String name) {
        return productRepo.findProductByNameLike(name);
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return productRepo.findProductByCategory(categoryId);
    }

    @Override
    public List<Product> findBySupplier(Long supplierId) {
        Supplier supplier = supplierService.findOne(supplierId);
        if(supplier == null) {
            return new ArrayList<Product>();
        }
        return productRepo.findProductBySupplier(supplier);
    }
    


}