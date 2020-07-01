package com.smartosc.product.controller;

import com.smartosc.product.dto.CategoryDTO;
import com.smartosc.product.entity.APIResponse;
import com.smartosc.product.service.CategoryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * product
 *
 * @author Tung lam
 * @created_at 22/06/2020 - 11:00
 * @created_by Tung lam
 * @since 22/06/2020
 */
@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private static final String SUCCESS = "Success";

    @GetMapping
    public ResponseEntity<APIResponse<List<CategoryDTO>>> getAllCategory() throws NotFoundException {
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategory();
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.OK.value(), SUCCESS, categoryDTOS), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryDTO>> getCategoryById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.OK.value(), SUCCESS, categoryService.getById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<CategoryDTO>> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.OK.value(), SUCCESS, categoryService.createCategory(categoryDTO)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse<CategoryDTO>> updateCategory( @RequestBody CategoryDTO categoryDTO) throws NotFoundException {
        return new ResponseEntity<>(new APIResponse<>(HttpStatus.OK.value(), SUCCESS, categoryService.update(categoryDTO)), HttpStatus.OK);
    }


}
