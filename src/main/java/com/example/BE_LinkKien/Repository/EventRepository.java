package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    boolean existsByName(String name);
    Event findEventById(Integer id);

}
