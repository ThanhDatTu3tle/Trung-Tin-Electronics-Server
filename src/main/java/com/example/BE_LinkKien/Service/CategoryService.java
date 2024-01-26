package com.example.BE_LinkKien.Service;


import com.example.BE_LinkKien.Images.Image;
import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Category;
import com.example.BE_LinkKien.Repository.CategoryRepository;
import com.example.BE_LinkKien.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Image imageService;
    public Category createCategory(String name, MultipartFile image)throws IOException {
        if(!categoryRepository.existsByName(name)) {
            Category category = new Category();
            category.setName(name);
            category.setImage(imageService.saveImage("images/category", image));
            category.setStatus(false);
            Category categoryInserted = categoryRepository.save(category);
            return categoryInserted;
        }else {
            throw new CustomException("Category is exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    public List<Category> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        if(categoryList == null) {
            throw new CustomException("The category list are empty", HttpStatus.NOT_FOUND);
        }
        return categoryList;
    }

    public Optional<Category> getCategoryById(Integer id){
        Optional<Category> _category = categoryRepository.findById(id);
        if(_category == null) {
            throw new CustomException("Category isn't exist", HttpStatus.NOT_FOUND);
        }
        return _category;
    }

    public Category editCategory(Integer id,String name,MultipartFile image) throws IOException {
        Category category = categoryRepository.findCategoryById(id);
        if (category != null) {
            try {
                if (category.getImage() != null) {
                    imageService.deleteImage(category.getImage());
                }
                category.setName(name);
                category.setImage(imageService.saveImage("images/category", image));
                Category saved = categoryRepository.save(category);
                return saved;
            } catch (Exception e) {
                throw new CustomException("Can't delete category", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new CustomException("category isn't exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void deleteCategory (Integer id){
        if(categoryRepository.existsById(id)) {
            Category category = categoryRepository.findCategoryById(id);
            try {
                if (category.getImage() != null) {
                    imageService.deleteImage(category.getImage());
                }
                categoryRepository.deleteById(id);
            } catch (Exception e) {
                throw new CustomException("Can't delete category", HttpStatus.NOT_FOUND);
            }
        }else {
            throw new CustomException("Category isn't exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
