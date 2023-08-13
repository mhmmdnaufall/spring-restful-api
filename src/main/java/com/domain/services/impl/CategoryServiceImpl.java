package com.domain.services.impl;

import java.util.Optional;

import com.domain.model.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.domain.model.entities.Category;
import com.domain.services.CategoryService;

import jakarta.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {

        // membedakan Update dan Insert
        if(category.getId() != null) {
            Category currentCategory = categoryRepository.findById(category.getId()).get();
            currentCategory.setName(category.getName());
            category = currentCategory;
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category findOne(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            return null;
        }
        return category.get();
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void removeOne(Long id) {
        categoryRepository.deleteById(id); // palingan cuman dikasih flag penanda sudah kehapus kah? true/false
                                     // apalagi disini akan men-delete column yang berelasi dengan tabel lain
                                     // jadi mungkin akan ada error
        
    }

    @Override
    public Iterable<Category> findByName(String name, Pageable pageable) {
        return categoryRepository.findByNameContains(name, pageable);
    }

    @Override
    public Iterable<Category> saveBatch(Iterable<Category> categories) {
        return categoryRepository.saveAll(categories);
    }    
}