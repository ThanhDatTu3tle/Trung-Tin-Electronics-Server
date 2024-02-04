package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.ComboDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboDetailRepository extends JpaRepository<ComboDetail, Integer> {
    List<ComboDetail> findComboDetailsByIdCombo(Integer id);

    void deleteAllByIdCombo(Integer id);
}
