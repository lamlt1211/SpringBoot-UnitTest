package com.smartosc.product.service;

import com.smartosc.product.dto.CategoryDTO;
import javassist.NotFoundException;

import java.util.List;

/**
 * product
 *
 * @author Tung lam
 * @created_at 22/06/2020 - 14:24
 * @created_by Tung lam
 * @since 22/06/2020
 */
public interface CategoryService {
    List<CategoryDTO> getAllCategory();

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO getById(Long id) throws NotFoundException;

    CategoryDTO update(CategoryDTO categoryDTO) throws NotFoundException;

    Boolean deleteCategory(Long id) throws NotFoundException;
}
