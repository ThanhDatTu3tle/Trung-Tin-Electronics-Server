package com.example.BE_LinkKien.Service;

import com.example.BE_LinkKien.Models.Combo;
import com.example.BE_LinkKien.Models.ComboDetail;
import com.example.BE_LinkKien.Models.Event;
import com.example.BE_LinkKien.Models.EventDetail;
// import com.example.BE_LinkKien.Models.Product;
import com.example.BE_LinkKien.Repository.EventDetailRepository;
import com.example.BE_LinkKien.Repository.EventRepository;
import com.example.BE_LinkKien.dto.EventDTO;
import com.example.BE_LinkKien.exception.CustomException;
import com.example.BE_LinkKien.payload.response.ComboResponse;
import com.example.BE_LinkKien.payload.response.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventDetailRepository eventDetailRepository;
    public EventResponse createEvent(EventDTO data) {
        if(!eventRepository.existsByName(data.getName())) {
            Event event = new Event();
            event.setName(data.getName());
            event.setImage(data.getImage());
            event.setPrice(data.getPrice());
            event.setCost(data.getCost());
            event.setDiscount(data.getDiscount());
            event.setStatus(false);
            Event event1 = eventRepository.save(event);

            EventResponse eventResponse = new EventResponse();
            List<EventDetail> eventDetailList = new ArrayList<>();
            eventResponse.setEvent(event1);
            data.getProduct().forEach((e)->{
                EventDetail eventDetail = new EventDetail();
                eventDetail.setIdProduct(e.getIdProduct());
                eventDetail.setIdEvent(event1.getId());
                eventDetail.setProductNumber(e.getQuantity());
                EventDetail detailSaved = eventDetailRepository.save(eventDetail);
                eventDetailList.add(detailSaved);
            });
            eventResponse.setDetail(eventDetailList);

            return eventResponse;
        }else {
            throw new CustomException("Event is exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public List<EventResponse> getAll() {
        List<EventResponse> eventResponseList = new ArrayList<>();
        List<Event> eventList = eventRepository.findAll();

        if(eventList == null) {
            throw new CustomException("The Event list are empty", HttpStatus.NOT_FOUND);
        }
        else
        {
            eventList.forEach((e)->{
                EventResponse eventResponse = new EventResponse();
                List<EventDetail> eventDetail = eventDetailRepository.findEventDetailsByIdEvent(e.getId());
                eventResponse.setEvent(e);
                eventResponse.setDetail(eventDetail);
                eventResponseList.add(eventResponse);
            });
        }
        return eventResponseList;
    }

    public EventResponse getById(Integer id){
        Event _event = eventRepository.findEventById(id);
        EventResponse eventResponse = new EventResponse();
        if(_event == null) {
            throw new CustomException("Event is not exists", HttpStatus.NOT_FOUND);
        }
        else
        {
            List<EventDetail> eventDetail = eventDetailRepository.findEventDetailsByIdEvent(_event.getId());
            eventResponse.setEvent(_event);
            eventResponse.setDetail(eventDetail);
        }
        return eventResponse;
    }

//    public Event editEvent(Event body) {
//        Optional<Event> _brand = eventRepository.findById(body.getId());
//        return _brand.map(category1 -> {
//            category1.setName(body.getName());
//            category1.setDiscount(body.getDiscount());
//            Event brandInserted = eventRepository.save(category1);
//            return brandInserted;
//        }).orElseThrow(() -> new CustomException("Event not found", HttpStatus.NOT_FOUND));
//    }

    public Event updateStatusEvent(Integer id, boolean status) {
        try{
            if(id == null)
            {
                throw new CustomException("ID is null", HttpStatus.BAD_REQUEST);
            }

            Event _event = eventRepository.findEventById(id);
            Event eventSaved = new Event();
            if(_event !=null)
            {
                _event.setStatus(status);
                eventSaved = eventRepository.save(_event);
            } else
            {
                throw new CustomException("Event is not exists", HttpStatus.BAD_REQUEST);
            }

            return eventSaved;
        } catch (Exception e) {
            throw new CustomException("Can not update status event", HttpStatus.NOT_FOUND);
        }
    }

    public void deleteEvent (Integer id){
        Event  event = eventRepository.findEventById(id);
        if(event != null)
        {
            List<EventDetail> eventDetailList = eventDetailRepository.findEventDetailsByIdEvent(event.getId());
            eventDetailList.forEach((e)->{
                eventDetailRepository.deleteById(e.getId());
            });
            eventRepository.deleteById(id);
        }else {
            throw new CustomException("Event is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}