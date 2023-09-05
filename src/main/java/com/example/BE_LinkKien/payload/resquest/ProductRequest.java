package com.example.BE_LinkKien.payload.resquest;

import com.example.BE_LinkKien.Models.ImageProduct;
import com.example.BE_LinkKien.Models.Specification;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductRequest {
    private String id;
    private String name;
    private String description;
    private ArrayList<String> specification;
    private ArrayList<ImageProductRequest> imageProducts;
    private Double price;
    private Integer idBrand;
    private Integer idCategory;


}
