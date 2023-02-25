package com.domain.model.repos;

import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository; // CrudRepository,PagingAndSortingRepository, dan fitur khusus lainnya (lebih lengkap)
import org.springframework.data.repository.CrudRepository; // otomatisasi crud
// import org.springframework.data.repository.PagingAndSortingRepository; // otomatisasi crud + paging&sorting

import com.domain.model.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {

    // Derived Query
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject

    // beberapa contoh :
    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    List<Supplier> findByNameStartingWith(String name);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}