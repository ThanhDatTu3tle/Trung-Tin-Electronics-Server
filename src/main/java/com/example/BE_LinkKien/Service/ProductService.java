package com.example.BE_LinkKien.Service;


import com.example.BE_LinkKien.Images.Image;
import com.example.BE_LinkKien.Models.*;
import com.example.BE_LinkKien.Repository.*;
import com.example.BE_LinkKien.dto.EventDTO;
import com.example.BE_LinkKien.dto.ImageProductDTO;
import com.example.BE_LinkKien.dto.SpecificationDTO;
import com.example.BE_LinkKien.exception.CustomException;
import com.example.BE_LinkKien.payload.response.ProductRespone;
import com.example.BE_LinkKien.payload.resquest.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SpecificationRepository specificationRepository;
    @Autowired
    private ImagePeoductRepository imagePeoductRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Image imageService;


    public Product createProduct(ProductRequest product) throws IOException
    {
        if(!productRepository.existsById(product.getId())) {
            try{
                Product _product = new Product();
                List<Specification> specificationList = new ArrayList<>();
                List<ImageProduct> imageProductList = new ArrayList<>();
                _product.setId(product.getId());
                _product.setName(product.getName());
                _product.setDescription(product.getDescription());
                _product.setPrice(product.getPrice());
                _product.setStatus(true);
                _product.setDiscount(product.getDiscount());
                _product.setPromotional(product.getPromotional());
                _product.setIdBrand(product.getIdBrand());
                _product.setIdCategory(product.getIdCategory());
                _product.setIdEvent(null);
                Product product1 = productRepository.save(_product);
                product.getSpecification().forEach((e)->{
                    Specification specification = new Specification();
                    specification.setSpecification(e);
                    specification.setIdProduct(product.getId());
                    specificationList.add(specification);
                });
                specificationRepository.saveAll(specificationList);
                product.getImageProducts().forEach((e)->{
                    ImageProduct imageProduct = new ImageProduct();
                    imageProduct.setImage(e.getImage());
                    imageProduct.setIdProduct(product.getId());
                    imageProductList.add(imageProduct);
                });
                imagePeoductRepository.saveAll(imageProductList);
                return product1;
            } catch (Exception e) {
                throw new CustomException("Can not create product", HttpStatus.NOT_FOUND);
            }
        }else {
            throw new CustomException("Product Id is exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    public List<ProductRespone> getAllProduct() {
        List<Product> products = productRepository.findAll();
        if(products == null) {
            throw new CustomException("The product list are empty", HttpStatus.NOT_FOUND);
        }
        try {
            List<ProductRespone> productResponeList = new ArrayList<>();
            products.stream().forEach((e) -> {
                ProductRespone productRespone = new ProductRespone();
                productRespone.setId(e.getId());
                productRespone.setName(e.getName());
                productRespone.setDescription(e.getDescription());
                List<Specification> specificationList = specificationRepository.findAllByIdProduct(e.getId());
                List<SpecificationDTO> specificationDTOS = specificationList.stream()
                        .map(accounts -> modelMapper.map(accounts, SpecificationDTO.class))
                        .collect(Collectors.toList());
                List<ImageProduct> imageProductList = imagePeoductRepository.findAllByIdProduct(e.getId());
                List<ImageProductDTO> imageProductDTOS = imageProductList.stream()
                        .map(accounts -> modelMapper.map(accounts, ImageProductDTO.class))
                        .collect(Collectors.toList());
                productRespone.setSpecification(specificationDTOS);
                productRespone.setImageProducts(imageProductDTOS);
                productRespone.setPrice(e.getPrice());
                productRespone.setQuantity(e.getQuantity());
                productRespone.setCreatedAt(e.getCreatedAt());
                productRespone.setCreatedBy(e.getCreatedBy());
                productRespone.setBrand(brandRepository.findBrandById(e.getIdBrand()));
                productRespone.setCategory(categoryRepository.findCategoryById(e.getIdCategory()));
                productRespone.setEvent(eventRepository.findEventById(e.getIdEvent()));
                productRespone.setStatus(e.getStatus());
                productRespone.setDiscount(e.getDiscount());
                productRespone.setPromotional(e.getPromotional());
                productRespone.setCreatedBy(userRepository.findUserById(e.getCreatedBy()).getEmail());
                productRespone.setCreatedAt(e.getCreatedAt());
                productRespone.setUpdatedBy(userRepository.findUserById(e.getUpdatedBy()).getEmail());
                productRespone.setUpdatedAt(e.getUpdatedAt());
                productResponeList.add(productRespone);
            });
            return productResponeList;
        } catch (Exception e) {
            throw new CustomException("Can not get product list", HttpStatus.NOT_FOUND);
        }
    }

    public ProductRespone getProductById(String id) {
        Product products = productRepository.findProductById(id);
        if(products == null) {
            throw new CustomException("The product is not exists", HttpStatus.NOT_FOUND);
        }
        ProductRespone productRespone = new ProductRespone();
        productRespone.setId(products.getId());
        productRespone.setName(products.getName());
        productRespone.setDescription(products.getDescription());
        List<Specification> specificationList = specificationRepository.findAllByIdProduct(products.getId());
        List<SpecificationDTO> specificationDTOS = specificationList.stream()
                .map(accounts -> modelMapper.map(accounts, SpecificationDTO.class))
                .collect(Collectors.toList());
        List<ImageProduct> imageProductList = imagePeoductRepository.findAllByIdProduct(products.getId());
        List<ImageProductDTO> imageProductDTOS = imageProductList.stream()
                .map(accounts -> modelMapper.map(accounts, ImageProductDTO.class))
                .collect(Collectors.toList());
        productRespone.setSpecification(specificationDTOS);
        productRespone.setImageProducts(imageProductDTOS);
        productRespone.setPrice(products.getPrice());
        productRespone.setQuantity(products.getQuantity());
        productRespone.setBrand(brandRepository.findBrandById(products.getIdBrand()));
        productRespone.setCategory(categoryRepository.findCategoryById(products.getIdCategory()));
        productRespone.setEvent(eventRepository.findEventById(products.getIdEvent()));
        productRespone.setStatus(products.getStatus());
        productRespone.setDiscount(products.getDiscount());
        productRespone.setPromotional(products.getPromotional());
        productRespone.setCreatedBy(userRepository.findUserById(products.getCreatedBy()).getEmail());
        productRespone.setCreatedAt(products.getCreatedAt());
        productRespone.setUpdatedBy(userRepository.findUserById(products.getUpdatedBy()).getEmail());
        productRespone.setUpdatedAt(products.getUpdatedAt());
        return productRespone;
    }

    public List<ProductRespone> getProductByIdCategory(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new CustomException("The ID list is empty", HttpStatus.BAD_REQUEST);
        }
        List<ProductRespone> productList = new ArrayList<>();
        for (Integer id : ids) {
            List<Product> products = productRepository.findProductByIdCategory(id);
            if(products != null) {
                products.stream().forEach((e) -> {
                    ProductRespone productRespone = new ProductRespone();
                    productRespone.setId(e.getId());
                    productRespone.setName(e.getName());
                    productRespone.setDescription(e.getDescription());
                    List<Specification> specificationList = specificationRepository.findAllByIdProduct(e.getId());
                    List<SpecificationDTO> specificationDTOS = specificationList.stream()
                            .map(accounts -> modelMapper.map(accounts, SpecificationDTO.class))
                            .collect(Collectors.toList());
                    List<ImageProduct> imageProductList = imagePeoductRepository.findAllByIdProduct(e.getId());
                    List<ImageProductDTO> imageProductDTOS = imageProductList.stream()
                            .map(accounts -> modelMapper.map(accounts, ImageProductDTO.class))
                            .collect(Collectors.toList());
                    productRespone.setSpecification(specificationDTOS);
                    productRespone.setImageProducts(imageProductDTOS);
                    productRespone.setPrice(e.getPrice());
                    productRespone.setQuantity(e.getQuantity());
                    productRespone.setCreatedAt(e.getCreatedAt());
                    productRespone.setCreatedBy(e.getCreatedBy());
                    productRespone.setBrand(brandRepository.findBrandById(e.getIdBrand()));
                    productRespone.setCategory(categoryRepository.findCategoryById(e.getIdCategory()));
                    productRespone.setEvent(eventRepository.findEventById(e.getIdEvent()));
                    productRespone.setStatus(e.getStatus());
                    productRespone.setDiscount(e.getDiscount());
                    productRespone.setPromotional(e.getPromotional());
                    productRespone.setCreatedBy(userRepository.findUserById(e.getCreatedBy()).getEmail());
                    productRespone.setCreatedAt(e.getCreatedAt());
                    productRespone.setUpdatedBy(userRepository.findUserById(e.getUpdatedBy()).getEmail());
                    productRespone.setUpdatedAt(e.getUpdatedAt());
                    productList.add(productRespone);
                });
            }
        }
        if (productList.isEmpty()) {
            throw new CustomException("No categories found for the provided IDs", HttpStatus.NOT_FOUND);
        }
        return productList;
    }

    public Product editProduct(ProductRequest body) {
        try{
            Product _product = productRepository.findProductById(body.getId());
            List<Specification> specificationList = new ArrayList<>();
            List<ImageProduct> imageProductList = new ArrayList<>();
//            _product.setId(body.getId());
            _product.setName(body.getName());
            _product.setDescription(body.getDescription());
            _product.setPrice(body.getPrice());
//            _product.setStatus(body.isStatus());
//            _product.setDiscount(body.getDiscount());
//            _product.setPromotional(body.getPromotional());
            _product.setIdBrand(body.getIdBrand());
            _product.setIdCategory(body.getIdCategory());
            _product.setIdEvent(null);
            Product product1 = productRepository.save(_product);
            return product1;
        } catch (Exception e) {
            throw new CustomException("Can not create product", HttpStatus.NOT_FOUND);
        }
    }

    public Product updateStatusProduct(String id, boolean status) {
        try{
            if(id == null)
            {
                throw new CustomException("ID is null", HttpStatus.BAD_REQUEST);
            }

            Product _product = productRepository.findProductById(id);
            Product productSaved = new Product();
            if(_product !=null)
            {
                _product.setStatus(status);
                productSaved = productRepository.save(_product);
            } else
            {
                throw new CustomException("Product is not exists", HttpStatus.BAD_REQUEST);
            }

            return productSaved;
        } catch (Exception e) {
            throw new CustomException("Can not update status product", HttpStatus.NOT_FOUND);
        }
    }

    public Product updateDiscount(String id, Integer discount, Double promotional) {
        try{
            if(id == null)
            {
                throw new CustomException("ID is null", HttpStatus.BAD_REQUEST);
            }

            Product _product = productRepository.findProductById(id);
            Product productSaved = new Product();
            if(_product !=null)
            {
                _product.setDiscount(discount);
                _product.setPromotional(promotional);
                productSaved = productRepository.save(_product);
            } else
            {
                throw new CustomException("Product is not exists", HttpStatus.BAD_REQUEST);
            }

            return productSaved;
        } catch (Exception e) {
            throw new CustomException("Can not update discount & price product", HttpStatus.NOT_FOUND);
        }
    }

    public List<Product> updateEventProduct(EventDTO data) {
        try{
            if(data == null)
            {
                throw new CustomException("ID is null", HttpStatus.BAD_REQUEST);
            }
            List<Product> listReturn = new ArrayList<>();
            data.getIdProducts().forEach((e)->{
                Product _product = productRepository.findProductById(e);
                Product productSaved = new Product();
                if(_product !=null)
                {
                    if(data.getIdEvent() ==0)
                    {
                        _product.setIdEvent(null);
                    }
                    else {
                        _product.setIdEvent(data.getIdEvent());
                    }
                    productSaved = productRepository.save(_product);
                    listReturn.add(productSaved);
                } else
                {
                    throw new CustomException("Product is not exists", HttpStatus.BAD_REQUEST);
                }
            });
            return listReturn;
        } catch (Exception e) {
            throw new CustomException("Can not update event for product", HttpStatus.NOT_FOUND);
        }
    }
    public Product updateQuantity(String id, boolean isAdd,Integer quantity) {
        try{
            if(id == null)
            {
                throw new CustomException("ID is null", HttpStatus.BAD_REQUEST);
            }
            if(quantity == null || quantity <=0)
            {
                throw new CustomException("Nhap lai so luong", HttpStatus.BAD_REQUEST);
            }
            Product _product = productRepository.findProductById(id);
            Product productSaved = new Product();
            int total = 0;
            if(_product !=null)
            {
               if(isAdd)
               {
                   if(_product.getQuantity() !=null)
                   {
                       total =_product.getQuantity() + quantity;
                   }else
                   {
                       total = quantity;
                   }
                   _product.setQuantity(total);
                   productSaved = productRepository.save(_product);
               }else {
                    if(_product.getQuantity() == null || quantity > _product.getQuantity())
                    {
                        throw new CustomException("So luong ton nho hon so luong mua", HttpStatus.BAD_REQUEST);
                    }
                    total = _product.getQuantity() - quantity;
                   _product.setQuantity(total);
                   productSaved = productRepository.save(_product);
               }
            } else
            {
                throw new CustomException("Product is not exists", HttpStatus.BAD_REQUEST);
            }

            return productSaved;
        } catch (Exception e) {
            throw new CustomException("Can not update quantity product", HttpStatus.NOT_FOUND);
        }
    }

    public void deleteProduct (Integer id){
        if(productRepository.existsById(String.valueOf(id))) {
            try {
                productRepository.deleteById(String.valueOf(id));
            } catch (Exception e) {
                throw new CustomException("Can't delete product", HttpStatus.NOT_FOUND);
            }
        }else {
            throw new CustomException("Product isn't exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Product updateProduct(ProductRequest product)
    {
        if(product != null) {
            try {
                Product _product = productRepository.findProductById(product.getId());
                if(_product ==null)
                {
                    throw new CustomException("Product is not exists", HttpStatus.BAD_REQUEST);
                }
                List<Specification> specificationList = new ArrayList<>();
                List<ImageProduct> imageProductList = new ArrayList<>();

//            _product.setId(product.getId());
                _product.setName(product.getName());
                _product.setDescription(product.getDescription());
                _product.setPrice(product.getPrice());
//            _product.setStatus(true);
//            _product.setDiscount(product.getDiscount());
                _product.setIdBrand(product.getIdBrand());
                _product.setIdCategory(product.getIdCategory());
//            _product.setIdEvent(null);
                Product product1 = productRepository.save(_product);
                //--------------------------------------------
                List<Specification> specificationDel = specificationRepository.findAllByIdProduct(product.getId());
                specificationDel.forEach((e)->{
                    e.setProduct(null);
                    specificationRepository.save(e);
                    specificationRepository.deleteById(e.getId());
                });
                List<ImageProduct> imageProductsDel = imagePeoductRepository.findAllByIdProduct(product.getId());
                imageProductsDel.forEach((e)->{
                    e.setProduct(null);
                    imagePeoductRepository.save(e);
                    imagePeoductRepository.deleteById(e.getId());
                });
                //--------------------------------------------

                product.getSpecification().forEach((e)->{
                    Specification specification = new Specification();
                    specification.setSpecification(e);
                    specification.setIdProduct(product.getId());
                    specificationList.add(specification);
                });
                specificationRepository.saveAll(specificationList);

                product.getImageProducts().forEach((e)->{
                    ImageProduct imageProduct = new ImageProduct();
//                    imageProduct.setImage(e.getImage());
                    imageProduct.setIdProduct(product.getId());
                    imageProductList.add(imageProduct);
                });
                imagePeoductRepository.saveAll(imageProductList);
                return product1;
            } catch (Exception e){
                throw new CustomException("Can't update product", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            throw new CustomException("Data is null", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
