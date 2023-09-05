package com.example.BE_LinkKien.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Product")
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
//    private List<Specification> specification;
    private Double price;
    private Boolean status;
    private Integer idBrand;
    private Integer idCategory;
    @Column(nullable = true)
    private Integer idEvent;

    //==================
    //Audit
    @JoinColumn(name = "created_by")
    @CreatedBy
    private String createdBy;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;


    @JoinColumn(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;
    //==================

    //Brand-> product
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idBrand",nullable = true,insertable = false,updatable = false)
    private Brand brand;
    //Category->product
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idCategory",nullable = true,insertable = false,updatable = false)
    private Category category;
    //Event->product
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idEvent",nullable = true,insertable = false,updatable = false)
    private Event event;
}
