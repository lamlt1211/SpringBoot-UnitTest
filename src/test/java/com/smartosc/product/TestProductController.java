package com.smartosc.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartosc.product.controller.ProductController;
import com.smartosc.product.dto.CategoryDTO;
import com.smartosc.product.dto.ProductDTO;
import com.smartosc.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

/**
 * product
 *
 * @author Tung lam
 * @created_at 10/06/2020 - 16:42
 * @created_by Tung lam
 * @since 10/06/2020
 */
@ExtendWith(SpringExtension.class)
public class TestProductController {

    @Mock
    private ProductService productService;
    private MockMvc mockMvc;

    private String url;

    private ObjectMapper objectMapper;

    private ProductDTO productDTO;

    private List<ProductDTO> productDTOList;

    private List<CategoryDTO> categoryDTOS;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        url = "/api/v1/product";

        categoryDTOS = new ArrayList<>();

        productDTO = new ProductDTO((long) 1, "trantuannam", new BigDecimal(5000), "dz", categoryDTOS);
        productDTOList = new ArrayList<>();
        productDTOList.add(productDTO);

        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();
    }

    // get all product
    @Test
    public void getAllProduct() throws Exception {
        lenient().when(productService.getAllProduct()).thenReturn(productDTOList);
        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(productDTOList.size())));
    }

    //create Product
    @Test
    public void createProduct() throws Exception {
        lenient().when(productService.createProduct(any())).thenReturn(productDTO);
        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(productDTO.getName())));
    }

    //updateUser
    @Test
    public void updateProduct() throws Exception {
        lenient().when(productService.updateProduct(any())).thenReturn(productDTO);
        this.mockMvc.perform(put(url, productDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(productDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductById() throws Exception {
        lenient().when(productService.getById(any())).thenReturn(productDTO);
        this.mockMvc.perform(get(url + "/{id}", productDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(productDTO.getName())));
    }

    @Test
    public void deleteProduct() throws Exception {
        lenient().when(productService.deleteProduct(any())).thenReturn(true);
        this.mockMvc.perform(delete(url + "/{id}", productDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk());
    }
}

