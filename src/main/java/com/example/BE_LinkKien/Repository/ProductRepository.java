package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByName(String name);

    List<Product> findAllByStatus(boolean status);

    Product findProductById (String Id);

    List<Product> findProductByIdCategory (Integer Id);
}
