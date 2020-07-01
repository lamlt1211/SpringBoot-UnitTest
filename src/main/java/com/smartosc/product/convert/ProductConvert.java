package com.smartosc.product.convert;

import com.smartosc.product.dto.CategoryDTO;
import com.smartosc.product.dto.ProductDTO;
import com.smartosc.product.entity.Category;
import com.smartosc.product.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * product
 *
 * @author Tung lam
 * @created_at 23/06/2020 - 14:01
 * @created_by Tung lam
 * @since 23/06/2020
 */
public class ProductConvert {
    public static ProductDTO EntityToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        List<Category> categories = product.getCategories(); //
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoryDTO.setDescription(category.getDescription());
            categoryDTOS.add(categoryDTO);
        }
        productDTO.setCategories(categoryDTOS);
        return productDTO;
    }

    public static Product DTOToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        List<CategoryDTO> categoryDTOS = productDTO.getCategories();
        List<Category> categories = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOS) {
            Category category = new Category();
            category.setId(categoryDTO.getId());
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            categories.add(category);
        }
        product.setCategories(categories);
        return product;
    }
}
