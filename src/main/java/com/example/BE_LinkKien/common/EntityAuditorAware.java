package com.example.BE_LinkKien.common;

import com.example.BE_LinkKien.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class EntityAuditorAware implements AuditorAware<String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<String> getCurrentAuditor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(userRepository.findByEmail(username).getId());
    }
}
