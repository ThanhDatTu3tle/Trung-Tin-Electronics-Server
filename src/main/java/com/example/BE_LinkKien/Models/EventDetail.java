package com.example.BE_LinkKien.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Event_Detail")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class EventDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idProduct;
    private Integer idEvent;
    private Integer productNumber;

    //product -> detail
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,optional = false)
    @JoinColumn(name ="idEvent",nullable = true,insertable = false,updatable = false)
    private Event event;

    //event -> detail
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,optional = false)
    @JoinColumn(name ="idProduct",nullable = true,insertable = false,updatable = false)
    private Product product;
}
