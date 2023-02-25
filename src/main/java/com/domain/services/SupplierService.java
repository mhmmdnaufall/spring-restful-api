package com.domain.services;

import java.util.List;

import com.domain.model.entities.Supplier;

public interface SupplierService {
    
    Supplier save(Supplier supplier);

    Supplier findOne(Long id);

    Iterable<Supplier> findall();

    void removeOne(Long id);

    Supplier findByEmail(String email);

    List<Supplier> findByName(String name);

    List<Supplier> findByNameStartWith(String name);

    List<Supplier> findByNameOrEmail(String name, String email);

}
