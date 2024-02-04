package com.example.BE_LinkKien.Service;

import com.example.BE_LinkKien.Models.Combo;
import com.example.BE_LinkKien.Models.ComboDetail;
// import com.example.BE_LinkKien.Models.Product;
import com.example.BE_LinkKien.Repository.ComboDetailRepository;
import com.example.BE_LinkKien.Repository.ComboRepository;
import com.example.BE_LinkKien.dto.ComboNoIdDTO;
import com.example.BE_LinkKien.exception.CustomException;
import com.example.BE_LinkKien.payload.response.ComboResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComboService {
    @Autowired
    private ComboRepository comboRepository;
    @Autowired
    private ComboDetailRepository comboDetailRepository;
    public ComboResponse createCombo(ComboNoIdDTO data) {
        if(!comboRepository.existsByName(data.getName())) {
            Combo combo = new Combo();
            combo.setName(data.getName());
            combo.setImage(data.getImage());
            combo.setPrice(data.getPrice());
            combo.setCost(data.getCost());
            combo.setDiscount(data.getDiscount());
            combo.setStatus(false);
            Combo combo1 = comboRepository.save(combo);

            ComboResponse comboResponse = new ComboResponse();
            List<ComboDetail> comboDetailList = new ArrayList<>();
            comboResponse.setCombo(combo1);
            data.getProduct().forEach((e)->{
                ComboDetail comboDetail = new ComboDetail();
                comboDetail.setIdProduct(e.getIdProduct());
                comboDetail.setIdCombo(combo1.getId());
                comboDetail.setProductNumber(e.getQuantity());
                ComboDetail detailSaved = comboDetailRepository.save(comboDetail);
                comboDetailList.add(detailSaved);
            });
            comboResponse.setDetail(comboDetailList);

            return comboResponse;
        }else {
            throw new CustomException("Combo is exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public List<ComboResponse> getAll() {
        List<ComboResponse> comboResponseList = new ArrayList<>();
        List<Combo> comboList = comboRepository.findAll();

        if(comboList == null) {
            throw new CustomException("The Combo list are empty", HttpStatus.NOT_FOUND);
        }
        else
        {
            comboList.forEach((e)->{
                ComboResponse comboResponse = new ComboResponse();
                List<ComboDetail> comboDetail = comboDetailRepository.findComboDetailsByIdCombo(e.getId());
                comboResponse.setCombo(e);
                comboResponse.setDetail(comboDetail);
                comboResponseList.add(comboResponse);
            });
        }
        return comboResponseList;
    }

    public ComboResponse getById(Integer id){
        Combo _combo = comboRepository.findComboById(id);
        ComboResponse comboResponse = new ComboResponse();
        if(_combo == null) {
            throw new CustomException("Combo is not exists", HttpStatus.NOT_FOUND);
        }
        else
        {
                List<ComboDetail> comboDetail = comboDetailRepository.findComboDetailsByIdCombo(_combo.getId());
                comboResponse.setCombo(_combo);
                comboResponse.setDetail(comboDetail);
        }
        return comboResponse;
    }
//    public ComboResponse editCombo(Integer id,ComboNoIdDTO data) {
//        Combo combo = comboRepository.findComboById(id);
//        if(combo != null)
//        {
//            combo.setName(data.getName());
//            combo.setImage(data.getImage());
//            combo.setPrice(data.getPrice());
//
//            Combo combo1 = comboRepository.save(combo);
//            ComboResponse comboResponse = new ComboResponse();
//            comboResponse.setCombo(combo1);
//
//            List<ComboDetail> comboDetailList = comboDetailRepository.findComboDetailsByIdCombo(combo.getId());
//            comboDetailList.forEach((e)->{
//                comboDetailRepository.deleteById(e.getId());
//            });
//
//            List<ComboDetail> comboDetailList1 = new ArrayList<>();
//
//            data.getProduct().forEach((e)->{
//                ComboDetail comboDetail = new ComboDetail();
//                comboDetail.setIdProduct(e.getIdProduct());
//                comboDetail.setIdCombo(combo1.getId());
//                comboDetail.setProductNumber(e.getQuantity());
//                ComboDetail detailSaved = comboDetailRepository.save(comboDetail);
//                comboDetailList1.add(detailSaved);
//            });
//            comboResponse.setDetail(comboDetailList1);
//
//            return  comboResponse;
//        }else {
//            throw new CustomException("Combo is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }

    public Combo updateStatusCombo(Integer id, boolean status) {
        try{
            if(id == null)
            {
                throw new CustomException("ID is null", HttpStatus.BAD_REQUEST);
            }

            Combo _combo = comboRepository.findComboById(id);
            Combo comboSaved = new Combo();
            if(_combo !=null)
            {
                _combo.setStatus(status);
                comboSaved = comboRepository.save(_combo);
            } else
            {
                throw new CustomException("Combo is not exists", HttpStatus.BAD_REQUEST);
            }

            return comboSaved;
        } catch (Exception e) {
            throw new CustomException("Can not update status combo", HttpStatus.NOT_FOUND);
        }
    }

    public void deleteCombo(Integer id){
        Combo  combo = comboRepository.findComboById(id);
        if(combo != null)
        {
            List<ComboDetail> comboDetailList = comboDetailRepository.findComboDetailsByIdCombo(combo.getId());
            comboDetailList.forEach((e)->{
                comboDetailRepository.deleteById(e.getId());
            });
            comboRepository.deleteById(id);
        }else {
            throw new CustomException("Combo is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}