package com.smartosc.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartosc.product.controller.ProductController;
import com.smartosc.product.dto.ProductDTO;
import com.smartosc.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
@SpringBootTest
public class TestController {

    @Mock
    private ProductService productService;
    private MockMvc mockMvc;

    private String url;

    private ObjectMapper objectMapper;

    private ProductDTO productDTO;

    private List<ProductDTO> productDTOList;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        url = "/api/v1/product";

        productDTO = new ProductDTO((long) 1, "trantuannam", new BigDecimal(5000), "dz");
        productDTOList = new ArrayList<>();
        productDTOList.add(productDTO);

        this.mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
        objectMapper = new ObjectMapper();
    }

    // get all product
    @Test
    public void getAllProduct() throws Exception {
        lenient().when(productService.getAllProduct()).thenReturn(productDTOList);
        this.mockMvc.perform(get(url + "/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(productDTOList.size())));
    }

    //create Product
    @Test
    public void create() throws Exception {
        lenient().when(productService.create(any())).thenReturn(productDTO);
        this.mockMvc.perform(post(url + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", is(productDTO.getName())));
    }

    //updateUser
    @Test
    public void update() throws Exception {
        lenient().when(productService.update(any(), any())).thenReturn(productDTO);
        this.mockMvc.perform(put(url + "/update/{id}", productDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(productDTO)))
                .andExpect(status().isOk());
    }
}
