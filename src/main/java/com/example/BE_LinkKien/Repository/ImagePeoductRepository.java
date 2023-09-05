package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.ImageProduct;
import com.example.BE_LinkKien.Models.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImagePeoductRepository extends JpaRepository<ImageProduct, Integer> {
    List<ImageProduct> findAllByIdProduct (String id);
    Integer deleteByIdProduct(String id);
}
