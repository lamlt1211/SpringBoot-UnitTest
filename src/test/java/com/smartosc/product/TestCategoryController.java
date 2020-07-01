package com.smartosc.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartosc.product.controller.CategoryController;
import com.smartosc.product.dto.CategoryDTO;
import com.smartosc.product.dto.ProductDTO;
import com.smartosc.product.entity.Product;
import com.smartosc.product.exceptions.NotFoundException;
import com.smartosc.product.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * product
 *
 * @author Tung lam
 * @created_at 26/06/2020 - 10:01
 * @created_by Tung lam
 * @since 26/06/2020
 */
@ExtendWith(SpringExtension.class)
public class TestCategoryController {
    private MockMvc mockMvc;

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    private ProductDTO productDTO;

    private List<ProductDTO> productDTOS;

    private ObjectMapper objectMapper;

    private CategoryDTO categoryDTO;

    private List<CategoryDTO> categoryDTOS;


    @BeforeEach
    public void initMocks() {

        productDTOS = new ArrayList<>();
        categoryDTO = new CategoryDTO(1L, "namcx", "hihi", productDTOS);
        categoryDTOS = new ArrayList<>();
        categoryDTOS.add(categoryDTO);

        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        objectMapper = new ObjectMapper();
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
//                .build();

    }

    @Test
    public void getAllCategorySuccess() throws Exception {
        List<CategoryDTO> categoryDTOList = Arrays.asList(new CategoryDTO(), new CategoryDTO());
        when(categoryService.getAllCategory()).thenReturn(categoryDTOList);
        mockMvc.perform(get("/api/v1/category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.*", hasSize(2)))
                .andDo(MockMvcResultHandlers.log());
    }


    @Test
    public void getCategoryById() throws Exception {
        lenient().when(categoryService.getById(any())).thenReturn(categoryDTO);
        this.mockMvc.perform(get("/api/v1/category/{id}", categoryDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(categoryDTO.getName())));
    }

    @Test
    public void createCategory() throws Exception {
        lenient().when(categoryService.createCategory(any())).thenReturn(categoryDTO);
        this.mockMvc.perform(post("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCategorySuccess() throws Exception {
        lenient().when(categoryService.update(any())).thenReturn(categoryDTO);
        this.mockMvc.perform(put("/api/v1/category", categoryDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(categoryDTO)))
                .andExpect(status().isOk());
    }
}
