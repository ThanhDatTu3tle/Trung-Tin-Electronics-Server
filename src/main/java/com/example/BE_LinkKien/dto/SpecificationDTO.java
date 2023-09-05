package com.example.BE_LinkKien.dto;


import com.example.BE_LinkKien.Enum.Role;
import lombok.Data;

import java.util.List;

@Data
public class SpecificationDTO {
    private Integer id;
    private String Specification;
    private String idProduct;
}
