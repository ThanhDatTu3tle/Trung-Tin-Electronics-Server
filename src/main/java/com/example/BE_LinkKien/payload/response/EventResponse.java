package com.example.BE_LinkKien.payload.response;

import com.example.BE_LinkKien.Models.Event;
import com.example.BE_LinkKien.Models.EventDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class EventResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Event event;
    private List<EventDetail> detail;
}
