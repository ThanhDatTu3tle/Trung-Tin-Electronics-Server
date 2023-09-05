package com.example.BE_LinkKien.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Brand")
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String image;
}
