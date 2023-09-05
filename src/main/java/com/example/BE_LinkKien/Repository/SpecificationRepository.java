package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Product;
import com.example.BE_LinkKien.Models.Specification;
import com.example.BE_LinkKien.dto.SpecificationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Integer> {

    List<Specification> findAllByIdProduct (String id);

    Integer deleteByIdProduct(String id);

}
