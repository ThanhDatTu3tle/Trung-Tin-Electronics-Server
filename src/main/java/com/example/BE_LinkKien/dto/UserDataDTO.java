package com.example.BE_LinkKien.dto;


import com.example.BE_LinkKien.Enum.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDataDTO {
//    private String username;
    private String email;
//    private String password;
    List<Role> userRoles;
    private String token;

}
