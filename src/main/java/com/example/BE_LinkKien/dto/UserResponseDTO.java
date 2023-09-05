package com.example.BE_LinkKien.dto;


import com.example.BE_LinkKien.Enum.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private int id;
    private String email;
    List<Role> role;
}
