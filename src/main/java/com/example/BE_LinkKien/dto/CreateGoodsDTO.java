package com.example.BE_LinkKien.dto;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CreateGoodsDTO {
    private Integer quantity;
    private Double cost;
    private List<GoodsDetailDTO> goodsDetail;
}
