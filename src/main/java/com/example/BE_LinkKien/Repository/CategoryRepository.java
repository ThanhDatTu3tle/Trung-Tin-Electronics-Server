package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);

    Category findCategoryById(Integer id);
}
