package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Service.BrandService;
import com.example.BE_LinkKien.Service.GoodsService;
import com.example.BE_LinkKien.Service.InvoiceService;
import com.example.BE_LinkKien.dto.BrandDTO;
import com.example.BE_LinkKien.dto.CreateGoodsDTO;
import com.example.BE_LinkKien.dto.GoodsDTO;
import com.example.BE_LinkKien.dto.InvoiceDTO;
import com.example.BE_LinkKien.payload.response.ResponseObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createInvoice(@RequestBody GoodsDTO data) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Create goods successfully",goodsService.createGoods(data)));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllGoods() {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all goods successfully",goodsService.getAllGoods()));
    }
}
