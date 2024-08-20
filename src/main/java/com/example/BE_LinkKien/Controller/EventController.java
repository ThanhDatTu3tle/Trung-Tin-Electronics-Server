package com.example.BE_LinkKien.Controller;

import com.example.BE_LinkKien.Models.Event;
import com.example.BE_LinkKien.Service.EventService;
import com.example.BE_LinkKien.dto.EventDTO;
import com.example.BE_LinkKien.payload.response.ResponseObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    public EventController(EventService eventService) {
//        this.eventService = eventService;
//    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createEvent(@RequestBody EventDTO data) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Create event successfully", eventService.createEvent(data)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get all event successfully", eventService.getAll()));
    }
    @GetMapping("/getEvent/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Get event successfully", eventService.getById(id)));
    }
    //    @PutMapping("/edit")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> editBrand(@RequestBody Event event) {
//        return ResponseEntity.ok().body(new ResponseObject("success",200, "Edit event successfully",eventService.editEvent(event)));
//    }
    @PutMapping("/updateStatus")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStatusEvent(@RequestParam Integer id,@RequestParam boolean status) {
        return ResponseEntity.ok().body(new ResponseObject("success", 200, "Edit status event successfully", eventService.updateStatusEvent(id,status)));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().body(new ResponseObject("success",200, "Delete event successfully",null));
    }
}