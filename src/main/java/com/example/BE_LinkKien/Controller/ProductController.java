package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Service.BrandService;
import com.example.BE_LinkKien.Service.ProductService;
import com.example.BE_LinkKien.dto.EventDTO;
import com.example.BE_LinkKien.payload.response.ResponseObject;
import com.example.BE_LinkKien.payload.resquest.ProductRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value ="/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest product) throws IOException {
        System.out.println(product);
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Create product successfully", productService.createProduct(product)));
    }
    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest product) {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Update product successfully", productService.updateProduct(product)));
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Get all product successfully", productService.getAllProduct()));
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Get all product successfully", productService.getProductById(id)));
    }

    @PostMapping("/getAllByIdCategory")
    public ResponseEntity<?> getAllProductByIdCategory(@RequestBody List<Integer> ids) {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Get product successfully", productService.getProductByIdCategory(ids)));
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editProduct(@RequestBody ProductRequest product1) {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Edit product successfully", productService.editProduct(product1)));
    }
    @PutMapping("/updateStatus")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStatusProduct(@RequestParam String id,@RequestParam boolean status) {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Edit status product successfully", productService.updateStatusProduct(id,status)));
    }
    @PutMapping("/updateDiscount")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateDiscount(@RequestParam String id,@RequestParam Integer discount,@RequestParam Double promotional) {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Edit discount product successfully", productService.updateDiscount(id,discount,promotional)));
    }
    @PutMapping("/updateQuantity")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateQuantity(@RequestParam String id, @RequestParam Integer quantity) {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Edit quantity product successfully", productService.updateQuantity(id,true,quantity)));
    }
//    @PutMapping("/updateIdEvent")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> updateEventProduct(@RequestBody EventDTO data) {
//        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Edit event for product successfully", productService.updateEventProduct(data)));
//    }
}
