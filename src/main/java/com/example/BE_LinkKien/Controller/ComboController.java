package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Models.Combo;
import com.example.BE_LinkKien.Service.ComboService;
import com.example.BE_LinkKien.dto.ComboNoIdDTO;
import com.example.BE_LinkKien.payload.response.ResponseObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/combo")
public class ComboController {

    private final ComboService comboService;

    @Autowired
    public ComboController(ComboService comboService) {
        this.comboService = comboService;
    }
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody ComboNoIdDTO data) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Create combo successfully",comboService.createCombo(data)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all combo successfully",comboService.getAll()));
    }
    @GetMapping("/getCombo/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get combo successfully",comboService.getById(id)));
    }
//    @PutMapping("/edit/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody ComboNoIdDTO data) {
//        return ResponseEntity.ok().body(new ResponseObject("success",200, "Edit combo successfully",comboService.editCombo(id,data)));
//    }
    @PutMapping("/updateStatus")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStatusCombo(@RequestParam Integer id,@RequestParam boolean status) {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Edit status combo successfully", comboService.updateStatusCombo(id,status)));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        comboService.deleteCombo(id);
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Delete combo successfully",null));
    }
}