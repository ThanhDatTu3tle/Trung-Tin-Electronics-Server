package com.example.BE_LinkKien.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "GoodsDetail")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class GoodsDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idGoods;
    private String idProduct;
    private Double cost;

    //product->goods detail
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idProduct",nullable = true,insertable = false,updatable = false)
    private Product product;

    //Invoice->goods detail
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idGoods",nullable = true,insertable = false,updatable = false)
    private Goods goods;
}
