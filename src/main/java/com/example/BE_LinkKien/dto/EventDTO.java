package com.example.BE_LinkKien.dto;

import lombok.Data;
import org.omg.CORBA.WStringSeqHelper;

import java.util.List;

@Data
public class EventDTO {
    private String name;
    private String image;
    private Double price;
    private Double cost;
    private Integer discount;
    private Boolean status;
    private List<ProductCombo> product;
}