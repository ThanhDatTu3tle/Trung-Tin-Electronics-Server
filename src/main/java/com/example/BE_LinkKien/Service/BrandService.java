package com.example.BE_LinkKien.Service;


import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Category;
import com.example.BE_LinkKien.Repository.BrandRepository;
import com.example.BE_LinkKien.Repository.CategoryRepository;
import com.example.BE_LinkKien.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;
    public Brand createBrand(String name, String image) {
        if(!brandRepository.existsByName(name)) {
            Brand brand = new Brand();
            brand.setName(name);
            brand.setImage(image);
            Brand brandInserted = brandRepository.save(brand);
            return brandInserted;

        }else {
            throw new CustomException("Brand is exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    public Brand createBrandTest(String name, String image) {

        if(!brandRepository.existsByName(name)) {
            Brand brand = new Brand();
            brand.setName(name);
            brand.setImage(image);
            Brand brandInserted = brandRepository.save(brand);
            return brandInserted;

        }else {
            throw new CustomException("Brand is exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    public List<Brand> getAllBrand() {
        List<Brand> brandList = brandRepository.findAll();
        if(brandList == null) {
            throw new CustomException("The brand list are empty", HttpStatus.NOT_FOUND);
        }
        return brandList;
    }

    public Optional<Brand> getById(Integer id){
        Optional<Brand> _brand = brandRepository.findById(id);
        if(_brand == null) {
            throw new CustomException("Brand isn't exist", HttpStatus.NOT_FOUND);
        }
        return _brand;
    }

    public Brand editBrand(Brand body) {
        Optional<Brand> _brand = brandRepository.findById(body.getId());
        return _brand.map(category1 -> {
            category1.setName(body.getName());
            category1.setImage(body.getImage());
            Brand brandInserted = brandRepository.save(category1);
            return brandInserted;
        }).orElseThrow(() -> new CustomException("Brand not found", HttpStatus.NOT_FOUND));
    }

    public void deleteBrand (Integer id){
        if(brandRepository.existsById(id)) {
            try {
                brandRepository.deleteById(id);
            } catch (Exception e) {
                throw new CustomException("Can't delete brand", HttpStatus.NOT_FOUND);
            }
        }else {
            throw new CustomException("Brand isn't exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}