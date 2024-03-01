package com.example.BE_LinkKien.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
@Data
public class GoodsDTO {
    private Integer id;
    private Integer quantity;
    private List<GoodsDetailDTO> goodsDetail;
    private String createdBy;
    private Timestamp createdAt;
    private String updatedBy;
    private Timestamp updatedAt;
}
