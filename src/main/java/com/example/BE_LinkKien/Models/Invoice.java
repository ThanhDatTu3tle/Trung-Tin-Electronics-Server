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
@Table(name = "Invoice")
@Data
//@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String phone;
    private String address;
    private String email;
    private String content;
    private Boolean status;
    private Double total;
    private String payment;
    //==================
    //Audit

//    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;


//    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;
    //==================
}
