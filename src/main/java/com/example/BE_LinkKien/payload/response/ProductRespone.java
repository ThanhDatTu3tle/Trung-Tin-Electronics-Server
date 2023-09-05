package com.example.BE_LinkKien.payload.response;

import com.example.BE_LinkKien.Models.Event;
import com.example.BE_LinkKien.Models.ImageProduct;
import com.example.BE_LinkKien.Models.Specification;
import com.example.BE_LinkKien.dto.ImageProductDTO;
import com.example.BE_LinkKien.dto.SpecificationDTO;
import com.example.BE_LinkKien.payload.resquest.ImageProductRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductRespone {
    private String id;
    private String name;
    private String description;
    private List<SpecificationDTO> specification;
    private List<ImageProductDTO> imageProducts;
    private Double price;
    private String brand;
    private Event event;
    private Boolean status;
    private String category;
    private String createdBy;
    private Timestamp createdAt;
    private String updatedBy;
    private Timestamp updatedAt;

}
