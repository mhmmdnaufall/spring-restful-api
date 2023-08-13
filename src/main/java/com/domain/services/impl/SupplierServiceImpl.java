package com.domain.services.impl;

import java.util.List;
import java.util.Optional;

import com.domain.model.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.domain.model.entities.Supplier;
import com.domain.services.SupplierService;

import jakarta.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier findOne(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.orElse(null);
    }

    @Override
    public Iterable<Supplier> findall() {
        return supplierRepository.findAll();
    }

    @Override
    public void removeOne(Long id) {
        supplierRepository.deleteById(id);
        
    }

    @Override
    public Supplier findByEmail(String email) {
        return supplierRepository.findByEmail(email);
    }

    @Override
    public List<Supplier> findByName(String name) {
        return supplierRepository.findByNameContainsOrderByIdDesc(name);
    }

    @Override
    public List<Supplier> findByNameStartWith(String name) {
        return supplierRepository.findByNameStartingWith(name);
    }

    @Override
    public List<Supplier> findByNameOrEmail(String name, String email) {
        return supplierRepository.findByNameContainsOrEmailContains(name, email);
    }
    
}
