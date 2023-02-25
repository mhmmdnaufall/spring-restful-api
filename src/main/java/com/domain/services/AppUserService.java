package com.domain.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.domain.model.entities.AppUser;


public interface AppUserService extends UserDetailsService {
    
    AppUser registerAppUser(AppUser user);

}
