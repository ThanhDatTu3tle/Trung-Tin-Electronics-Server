package com.example.BE_LinkKien.Service;

import com.example.BE_LinkKien.Models.User;
import com.example.BE_LinkKien.Repository.UserRepository;
import com.example.BE_LinkKien.dto.UserDataDTO;
import com.example.BE_LinkKien.exception.CustomException;
import com.example.BE_LinkKien.payload.response.ResponseObject;
import com.example.BE_LinkKien.payload.response.TokenResponse;
import com.example.BE_LinkKien.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private final UserRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<?> signin(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String token = jwtTokenProvider.createToken(email, accountRepository.findByEmail(email).getRole());
            UserDataDTO userDataDTO = new UserDataDTO();
            User user = accountRepository.findByEmail(email);
            userDataDTO.setEmail(user.getEmail());
            userDataDTO.setUserRoles(user.getRole());
            userDataDTO.setToken(token);
            return ResponseEntity.ok().body(new ResponseObject("success", 200, "Logged in successfully", userDataDTO));
        }catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<?> signup(User user) {
        if(!accountRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            accountRepository.save(user);
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
            return ResponseEntity.ok().body(new ResponseObject("success", 200, "Account created successfully", new TokenResponse(token)));
        }else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
