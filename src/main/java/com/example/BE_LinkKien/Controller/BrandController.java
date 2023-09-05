package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Service.BrandService;
import com.example.BE_LinkKien.payload.response.ResponseObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createBrand(@RequestParam String name, @RequestParam String image) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Create brand successfully",brandService.createBrandTest(name,image)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all category successfully",brandService.getAllBrand()));
    }
    @GetMapping("/getBrand/{id}")
    public ResponseEntity<?> getBrand(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get brand successfully",brandService.getById(id)));
    }
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editBrand(@PathVariable Integer id,@RequestBody Brand brand) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Edit brand successfully",brandService.editBrand(brand)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Delete brand successfully",null));
    }
}