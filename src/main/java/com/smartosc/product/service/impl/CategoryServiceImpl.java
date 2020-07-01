package com.smartosc.product.service.impl;

import com.smartosc.product.convert.CategoryConvert;
import com.smartosc.product.convert.ProductConvert;
import com.smartosc.product.dto.CategoryDTO;
import com.smartosc.product.dto.ProductDTO;
import com.smartosc.product.entity.Category;
import com.smartosc.product.entity.Product;
import com.smartosc.product.exceptions.NotFoundException;
import com.smartosc.product.repository.CategoryRepository;
import com.smartosc.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * product
 *
 * @author Tung lam
 * @created_at 22/06/2020 - 14:26
 * @created_by Tung lam
 * @since 22/06/2020
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO>  getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Category category : categories) {
            if (category.getProducts() != null) {
                List<Product> products = category.getProducts();
                for (Product product : products) {
                    ProductDTO productDTO = ProductConvert.EntityToDTO(product);
                    productDTOS.add(productDTO);
                }
            } else {
                throw new NotFoundException("Not found");
            }
            CategoryDTO categoryDTO = CategoryConvert.EntityToDTO(category);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findByName(categoryDTO.getName());
        if (category != null) {
            throw new DuplicateKeyException("Danh muc da ton tai");
        }
        Category category1 = modelMapper.map(categoryDTO, Category.class);
        return modelMapper.map(categoryRepository.save(category1), CategoryDTO.class);
    }

    @Override
    public CategoryDTO getById(Long id) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        CategoryDTO categoryDTO;
        if (optionalCategory.isPresent()) {
            categoryDTO = CategoryConvert.EntityToDTO(optionalCategory.get());
            return categoryDTO;
        } else {
            throw new NotFoundException("Id " + id + " khong tim thay");
        }
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getId());
        if (optionalCategory.isPresent()) {
            Category category = modelMapper.map(categoryDTO, Category.class);
            categoryRepository.save(category);
            return categoryDTO;
        } else {
            throw new NotFoundException("Category khong tim thay");
        }
    }

    @Override
    public Boolean deleteCategory(Long id) throws NotFoundException {
        return null;
    }
}
