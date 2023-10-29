package com.example.BE_LinkKien.dto;


import lombok.Data;

import javax.persistence.Column;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    //    private List<Specification> specification;
    private Double price;
    private Boolean status;
    private Integer idBrand;
    private Integer idCategory;
    private Integer idEvent;
    private Integer quantity;

}