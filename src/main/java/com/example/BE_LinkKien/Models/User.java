package com.example.BE_LinkKien.Models;

import com.example.BE_LinkKien.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> role;
}
