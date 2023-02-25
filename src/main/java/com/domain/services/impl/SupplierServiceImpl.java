package com.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.model.entities.Supplier;
import com.domain.model.repos.SupplierRepo;
import com.domain.services.SupplierService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    @Override
    public Supplier findOne(Long id) {
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if(supplier.isEmpty()) {
            return null;
        }
        return supplier.get();
    }

    @Override
    public Iterable<Supplier> findall() {
        return supplierRepo.findAll();
    }

    @Override
    public void removeOne(Long id) {
        supplierRepo.deleteById(id);
        
    }

    @Override
    public Supplier findByEmail(String email) {
        return supplierRepo.findByEmail(email);
    }

    @Override
    public List<Supplier> findByName(String name) {
        return supplierRepo.findByNameContainsOrderByIdDesc(name);
    }

    @Override
    public List<Supplier> findByNameStartWith(String name) {
        return supplierRepo.findByNameStartingWith(name);
    }

    @Override
    public List<Supplier> findByNameOrEmail(String name, String email) {
        return supplierRepo.findByNameContainsOrEmailContains(name, email);
    }
    
}
