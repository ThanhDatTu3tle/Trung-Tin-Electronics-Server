package com.example.BE_LinkKien.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    private Boolean status;
}
