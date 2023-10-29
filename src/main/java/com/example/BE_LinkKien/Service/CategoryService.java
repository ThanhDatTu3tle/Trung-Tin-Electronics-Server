package com.example.BE_LinkKien.Service;


import com.example.BE_LinkKien.Models.Category;
import com.example.BE_LinkKien.Repository.CategoryRepository;
import com.example.BE_LinkKien.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    public Category createCategory(String name, String image) {
        if(!categoryRepository.existsByName(name)) {
            Category category = new Category();
            category.setName(name);
            category.setImage(image);
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

    public Category editCategory(Category body) {
        Optional<Category> _category = categoryRepository.findById(body.getId());
        return _category.map(category1 -> {
            category1.setName(body.getName());
            category1.setImage(body.getImage());
            category1.setStatus(body.getStatus());
            Category categoryInserted = categoryRepository.save(category1);
            return categoryInserted;
        }).orElseThrow(() -> new CustomException("Category not found", HttpStatus.NOT_FOUND));
    }

    public void deleteCategory (Integer id){
        if(categoryRepository.existsById(id)) {
            try {
                categoryRepository.deleteById(id);
            } catch (Exception e) {
                throw new CustomException("Can't delete category", HttpStatus.NOT_FOUND);
            }
        }else {
            throw new CustomException("Category isn't exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
