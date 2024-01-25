package com.example.BE_LinkKien.dto;


import lombok.Data;

import java.util.List;

@Data
public class ComboDTO {
    private Integer idCombo;
    private List<String> idProducts;

}