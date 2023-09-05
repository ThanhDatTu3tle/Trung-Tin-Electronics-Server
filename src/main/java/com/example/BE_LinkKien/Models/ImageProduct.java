package com.example.BE_LinkKien.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Image")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    private String idProduct;

    //product->image
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idProduct",nullable = true,insertable = false,updatable = false)
    private Product product;
}
