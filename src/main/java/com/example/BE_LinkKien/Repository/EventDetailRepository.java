package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.EventDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDetailRepository extends JpaRepository<EventDetail, Integer> {
    List<EventDetail> findEventDetailsByIdEvent(Integer id);

    void deleteAllByIdEvent(Integer id);
}
