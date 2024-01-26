package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Models.User;
import com.example.BE_LinkKien.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        return userService.signin(email, password);
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> regis(@RequestBody User user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

}
