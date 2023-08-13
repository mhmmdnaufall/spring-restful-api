package com.domain.model.repository;

import com.domain.model.entities.Category;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository; // CrudRepository,PagingAndSortingRepository, dan fitur khusus lainnya (lebih lengkap)
// import org.springframework.data.repository.CrudRepository; // otomatisasi crud
import org.springframework.data.repository.PagingAndSortingRepository; // otomatisasi crud + paging&sorting

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    Page<Category> findByNameContains(String name, Pageable pageable);

    Category save(Category category);

    Optional<Category> findById(Long id);

    Iterable<Category> findAll();

    void deleteById(Long id);

    Iterable<Category> saveAll(Iterable<Category> categories);

}