package com.example.BE_LinkKien.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "InvoiceDetail")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idInvoice;
    private String idProduct;
    private Integer number;

    //product->invoice detail
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idProduct",nullable = true,insertable = false,updatable = false)
    private Product product;

    //Invoice->invoice detail
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idInvoice",nullable = true,insertable = false,updatable = false)
    private Invoice invoice;
}
