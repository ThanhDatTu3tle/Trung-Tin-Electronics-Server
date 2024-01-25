package com.example.BE_LinkKien.dto;


import lombok.Data;

import java.util.List;

@Data
public class EventDTO {
    private Integer idEvent;
    private List<String> idProducts;

}