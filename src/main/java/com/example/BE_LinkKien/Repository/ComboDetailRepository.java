package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Combo;
import com.example.BE_LinkKien.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer> {
    boolean existsByName(String name);
    Combo findComboById(Integer id);

}
