package com.example.BE_LinkKien.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Combo")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    private Double price;
    private Double cost;
    private Integer discount;
    private Boolean status;
}
