package com.example.BE_LinkKien.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class InvoiceDTO {
    private Integer id;
    private String customerName;
    private String phone;
    private String address;
    private String email;
    private String content;
    private Boolean status;
    private Boolean confirm;
    private Double total;
    private String payment;
    private List<InvoiceDetailDTO> invoiceDetail;
    private String createdBy;
    private Timestamp createdAt;
    private String updatedBy;
    private Timestamp updatedAt;
}
