package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Service.BrandService;
import com.example.BE_LinkKien.Service.InvoiceService;
import com.example.BE_LinkKien.dto.BrandDTO;
import com.example.BE_LinkKien.dto.CreateInvoiceDTO;
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
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/create")
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceDTO data) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Create invoice successfully",invoiceService.createInvpoce(data)));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllInvoice() {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all invoice successfully",invoiceService.getAllInvoice()));
    }
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getInvoice(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get invoice successfully",invoiceService.getInvoiceById(id)));
    }
    @PutMapping("/updateStatus")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStatus(@RequestParam Integer id,@RequestParam boolean status) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all invoice successfully",invoiceService.updateStatusInvoice(id,status)));
    }

}
