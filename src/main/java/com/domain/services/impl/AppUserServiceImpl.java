package com.domain.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.model.repository.AppUserRepository;
import com.domain.model.entities.AppUser;
import com.domain.services.AppUserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
            .orElseThrow(() -> 
                new UsernameNotFoundException(
                    String.format("user with email \"%s\" not found", email)
                ));
    }

    // Register User with bCryptPasswordEncoder and save to database
    @Override
    public AppUser registerAppUser(AppUser user) {

        boolean userExists = appUserRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new RuntimeException(
                String.format("user with email \"%s\" already exist", user.getEmail())
            );
        }

        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        return appUserRepository.save(user);
    }
    
}
