package com.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.model.repos.AppUserRepo;
import com.domain.model.entities.AppUser;
import com.domain.services.AppUserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email)
            .orElseThrow(() -> 
                new UsernameNotFoundException(
                    String.format("user with email \"%s\" not found", email)
                ));
    }

    // Register User with bCryptPasswordEncoder and save to database
    @Override
    public AppUser registerAppUser(AppUser user) {

        boolean userExists = appUserRepo.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new RuntimeException(
                String.format("user with email \"%s\" already exist", user.getEmail())
            );
        }

        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        return appUserRepo.save(user);
    }
    
}
