package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Models.Category;
import com.example.BE_LinkKien.Models.User;
import com.example.BE_LinkKien.Service.CategoryService;
import com.example.BE_LinkKien.Service.UserService;
import com.example.BE_LinkKien.payload.response.ResponseObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createCategory(@RequestParam String name, @RequestParam String image) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Create category successfully",categoryService.createCategory(name,image)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all category successfully",categoryService.getAllCategory()));
    }
    @GetMapping("/getCategory/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get category successfully",categoryService.getCategoryById(id)));
    }
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editCategory(@PathVariable Integer id,@RequestBody Category category1) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Edit category successfully",categoryService.editCategory(category1)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Delete category successfully",null));
    }
}
