package com.example.BE_LinkKien.Service;


import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Event;
import com.example.BE_LinkKien.Repository.BrandRepository;
import com.example.BE_LinkKien.Repository.EventRepository;
import com.example.BE_LinkKien.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    public Event createEvent(String name, Integer discount) {
        if(!eventRepository.existsByName(name)) {
            Event brand = new Event();
            brand.setName(name);
            brand.setDiscount(discount);
            Event brandInserted = eventRepository.save(brand);
            return brandInserted;
        }else {
            throw new CustomException("Event is exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public List<Event> getAllEvent() {
        List<Event> brandList = eventRepository.findAll();
        if(brandList == null) {
            throw new CustomException("The Event list are empty", HttpStatus.NOT_FOUND);
        }
        return brandList;
    }

    public Optional<Event> getById(Integer id){
        Optional<Event> _brand = eventRepository.findById(id);
        if(_brand == null) {
            throw new CustomException("Event isn't exist", HttpStatus.NOT_FOUND);
        }
        return _brand;
    }

    public Event editEvent(Event body) {
        Optional<Event> _brand = eventRepository.findById(body.getId());
        return _brand.map(category1 -> {
            category1.setName(body.getName());
            category1.setDiscount(body.getDiscount());
            Event brandInserted = eventRepository.save(category1);
            return brandInserted;
        }).orElseThrow(() -> new CustomException("Event not found", HttpStatus.NOT_FOUND));
    }

    public void deleteEvent (Integer id){
        if(eventRepository.existsById(id)) {
            try {
                eventRepository.deleteById(id);
            } catch (Exception e) {
                throw new CustomException("Can't delete Event", HttpStatus.NOT_FOUND);
            }
        }else {
            throw new CustomException("Event isn't exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}