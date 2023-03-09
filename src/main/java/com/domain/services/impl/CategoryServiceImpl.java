package com.domain.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.domain.model.entities.Category;
import com.domain.model.repos.CategoryRepo;
import com.domain.services.CategoryService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category save(Category category) {

        // membedakan Update dan Insert
        if(category.getId() != null) {
            Category currentCategory = categoryRepo.findById(category.getId()).get();
            currentCategory.setName(category.getName());
            category = currentCategory;
        }

        return categoryRepo.save(category);
    }

    @Override
    public Category findOne(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if(category.isEmpty()) {
            return null;
        }
        return category.get();
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public void removeOne(Long id) {
        categoryRepo.deleteById(id); // palingan cuman dikasih flag penanda sudah kehapus kah? true/false
                                     // apalagi disini akan men-delete column yang berelasi dengan tabel lain
                                     // jadi mungkin akan ada error
        
    }

    @Override
    public Iterable<Category> findByName(String name, Pageable pageable) {
        return categoryRepo.findByNameContains(name, pageable);
    }

    @Override
    public Iterable<Category> saveBatch(Iterable<Category> categories) {
        return categoryRepo.saveAll(categories);
    }    
}