package com.domain.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.domain.model.entities.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
 
    Optional<AppUser> findByEmail(String email);

}
