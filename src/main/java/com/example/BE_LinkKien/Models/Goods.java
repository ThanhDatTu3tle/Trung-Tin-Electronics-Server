package com.example.BE_LinkKien.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Goods")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;

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
