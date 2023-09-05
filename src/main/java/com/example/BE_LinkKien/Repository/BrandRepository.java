package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByName(String name);
    Brand findBrandById(Integer id);

}
