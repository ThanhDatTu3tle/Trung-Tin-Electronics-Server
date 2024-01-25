package com.example.BE_LinkKien.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Combo_Detail")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Combo_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idProduct;
    private Integer idCombo;
    private Integer productNumber;

    //product -> detail
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idCombo",nullable = true,insertable = false,updatable = false)
    private Combo combo;

    //combo -> detail
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idProduct",nullable = true,insertable = false,updatable = false)
    private Product product;
}
