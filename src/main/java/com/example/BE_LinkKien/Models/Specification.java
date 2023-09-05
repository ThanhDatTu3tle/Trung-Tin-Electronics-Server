package com.example.BE_LinkKien.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "Specification")
@Data
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Specification;
    private String idProduct;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name ="idProduct",nullable = true,insertable = false,updatable = false)
    private Product product;
}
