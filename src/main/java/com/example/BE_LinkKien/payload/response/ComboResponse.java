package com.example.BE_LinkKien.payload.response;

import com.example.BE_LinkKien.Models.Combo;
import com.example.BE_LinkKien.Models.ComboDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ComboResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Combo combo;
    private List<ComboDetail> detail;
}
