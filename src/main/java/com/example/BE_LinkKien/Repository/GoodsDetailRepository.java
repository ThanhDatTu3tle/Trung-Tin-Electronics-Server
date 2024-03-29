package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Goods;
import com.example.BE_LinkKien.Models.GoodsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDetailRepository extends JpaRepository<GoodsDetail, Integer> {
    List<GoodsDetail> findAllByIdGoods(Integer id);
}
