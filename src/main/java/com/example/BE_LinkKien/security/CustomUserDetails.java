package com.example.BE_LinkKien.security;

//import com.example.BE.Models.Accounts;
//import com.example.BE.Repository.AccountRepository;
import com.example.BE_LinkKien.Models.User;
import com.example.BE_LinkKien.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private final UserRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User accounts = accountRepository.findByEmail(email);

        if(accounts == null) {
            throw new UsernameNotFoundException("User "+ email + " not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(accounts.getPassword())
                .authorities(accounts.getRole())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
