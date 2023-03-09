package com.domain.util;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.domain.model.entities.AppUser;

public class AuditorAwareImpl implements AuditorAware<String> {

    // memberi tahu spring user saat ini
    @Override
    public Optional<String> getCurrentAuditor() {
        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(currentUser.getEmail());
    }
    
}
