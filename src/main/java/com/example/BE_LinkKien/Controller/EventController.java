package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Event;
import com.example.BE_LinkKien.Service.BrandService;
import com.example.BE_LinkKien.Service.EventService;
import com.example.BE_LinkKien.payload.response.ResponseObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createBrand(@RequestParam String name, @RequestParam Integer disscount) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Create event successfully",eventService.createEvent(name,disscount)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all event successfully",eventService.getAllEvent()));
    }
    @GetMapping("/getEvent/{id}")
    public ResponseEntity<?> getBrand(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get event successfully",eventService.getById(id)));
    }
    @PutMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editBrand(@RequestBody Event brand) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Edit event successfully",eventService.editEvent(brand)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Delete brand successfully",null));
    }
}