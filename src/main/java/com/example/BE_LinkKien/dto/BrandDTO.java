package com.example.BE_LinkKien.dto;


import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BrandDTO {
    private Integer id;
    private String name;
    private String imageUrl;

}