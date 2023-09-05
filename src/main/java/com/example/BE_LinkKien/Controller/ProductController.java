package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Service.BrandService;
import com.example.BE_LinkKien.Service.ProductService;
import com.example.BE_LinkKien.payload.response.ResponseObject;
import com.example.BE_LinkKien.payload.resquest.ProductRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest product) {
        System.out.println(product);
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Create product successfully",productService.createProduct(product)));
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all product successfully",productService.getAllProduct()));
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all product successfully",productService.getProductById(id)));
    }
}
