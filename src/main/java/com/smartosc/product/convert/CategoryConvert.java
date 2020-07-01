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
 * @created_at 23/06/2020 - 14:20
 * @created_by Tung lam
 * @since 23/06/2020
 */
public class CategoryConvert {
    public static CategoryDTO EntityToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        List<Product> products = category.getProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTOS.add(productDTO);
        }
        categoryDTO.setProducts(productDTOS);
        return categoryDTO;
    }

    public static Category DTOToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(category.getId());
        category.setName(category.getName());
        category.setDescription(category.getDescription());
        List<ProductDTO> productDTOS = categoryDTO.getProducts();
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : productDTOS) {
            Product product = new Product();
            product.setId(productDTO.getId());
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            products.add(product);
        }
        category.setProducts(products);
        return category;
    }

}
