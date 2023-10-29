package com.example.BE_LinkKien.dto;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CreateInvoiceDTO {
    private String customerName;
    private String phone;
    private String address;
    private String email;
    private String content;
    private Boolean status;
    private Double total;
    private String payment;
    private List<InvoiceDetailDTO> invoiceDetail;
}
