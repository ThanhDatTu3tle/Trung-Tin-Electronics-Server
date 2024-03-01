package com.example.BE_LinkKien.Service;

import com.example.BE_LinkKien.Models.*;
import com.example.BE_LinkKien.Repository.BrandRepository;
import com.example.BE_LinkKien.Repository.GoodsDetailRepository;
import com.example.BE_LinkKien.Repository.GoodsRepository;
import com.example.BE_LinkKien.Repository.ProductRepository;
import com.example.BE_LinkKien.dto.*;
import com.example.BE_LinkKien.exception.CustomException;
import com.example.BE_LinkKien.payload.response.ProductRespone;
import com.example.BE_LinkKien.payload.resquest.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.sql.Timestamp;
import java.time.Instant;
@Service
@RequiredArgsConstructor
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private GoodsDetailRepository goodsDetailRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    public Goods createGoods(GoodsDTO data)
    {
        try{
            Goods goods = new Goods();
            Date date = new Date();
            // Chuyển đổi thời gian hiện tại thành đối tượng Instant
            Instant instant = date.toInstant();

            // Chuyển đổi đối tượng Instant thành đối tượng Timestamp
            Timestamp timestamp = Timestamp.from(instant);

            List<GoodsDetail> goodsDetailList = new ArrayList<>();
            goods.setQuantity(data.getQuantity());
            goods.setCreatedAt(timestamp);

            Goods idGoods = goodsRepository.save(goods);
//            Invoice idInvoice = new Invoice();
            data.getGoodsDetail().forEach((e)->{
                GoodsDetail goodsDetail1 = new GoodsDetail();
                goodsDetail1.setIdGoods(idGoods.getId());
                goodsDetail1.setIdProduct(e.getIdProduct());
                goodsDetail1.setCost(e.getCost());
                goodsDetailList.add(goodsDetail1);
//                productService.updateQuantity(e.getIdProduct(),false,e.getNumber());
            });
            goodsDetailRepository.saveAll(goodsDetailList);
            return idGoods;
        }
        catch (Exception e){
            throw new CustomException("Cant add goods", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<GoodsDTO> getAllGoods() {
        List<Goods> goodss = goodsRepository.findAll();
        if(goodss == null) {
            throw new CustomException("The goodss list are empty", HttpStatus.NOT_FOUND);
        }

        List<GoodsDTO> goodsDTOList = new ArrayList<>();


        goodss.stream().forEach((e)->{
            GoodsDTO goodsDTO = new GoodsDTO();
            goodsDTO.setId(e.getId());
            goodsDTO.setQuantity(e.getQuantity());

            goodsDTO.setCreatedAt(e.getCreatedAt());
            goodsDTO.setUpdatedAt(e.getUpdatedAt());


            List<GoodsDetail> goodsDetailList = goodsDetailRepository.findAllByIdGoods(e.getId());
            List<GoodsDetailDTO> goodsDetailDTOList = goodsDetailList.stream()
                    .map(accounts -> modelMapper.map(accounts, GoodsDetailDTO.class))
                    .collect(Collectors.toList());
            goodsDTO.setGoodsDetail(goodsDetailDTOList);
            goodsDTOList.add(goodsDTO);
        });
        return goodsDTOList;
    }
}
