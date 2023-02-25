package com.domain.model.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.domain.model.entities.AppUser;

public interface AppUserRepo extends CrudRepository<AppUser, Long> {
 
    Optional<AppUser> findByEmail(String email);

}
