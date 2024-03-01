package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    Goods findGoodsById(Integer id);
}
