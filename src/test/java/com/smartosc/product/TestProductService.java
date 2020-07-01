package com.smartosc.product;

import com.smartosc.product.dto.CategoryDTO;
import com.smartosc.product.dto.ProductDTO;
import com.smartosc.product.entity.Category;
import com.smartosc.product.entity.Product;
import com.smartosc.product.repository.ProductRepository;
import com.smartosc.product.service.ProductService;
import com.smartosc.product.service.impl.ProductServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class TestProductService {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();

    private Product product;
    private ProductDTO productDTO;
    private List<Product> productList;
    private List<ProductDTO> productDTOList;
    private List<CategoryDTO> categoryDTOS;
    private List<Category> categories;
    private Category category;

    @BeforeEach
    public void setUp() {
        categoryDTOS = new ArrayList<>();

        category = new Category(1L, "category", "emak");
        categories = new ArrayList<>();
        categories.add(category);


        product = new Product(1L, "3", new BigDecimal(4), "6", categories);
        productList = new ArrayList<>();
        productList.add(product);

        productDTO = new ProductDTO(1L, "3", new BigDecimal(4), "6", categoryDTOS);
        productDTOList = new ArrayList<>();
        productDTOList.add(productDTO);
    }

    @Test
    void getAllProduct() throws NotFoundException {
        lenient().when(productRepository.findAll()).thenReturn(productList);
        List<ProductDTO> productDTOdata = productService.getAllProduct();
        Assertions.assertEquals(productDTOdata.size(), productList.size());
    }

    @Test
    void getAllProductFail() {
        lenient().when(productRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NotFoundException.class, () -> {
            productService.getAllProduct();
        });
    }

    @Test
    void getProductByIdSuccess() throws NotFoundException {
        lenient().when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
        ProductDTO dto = productService.getById(productDTO.getId());
        Assertions.assertEquals(dto.getId(), product.getId());
    }

    @Test
    void getProductByIdFail() {
        lenient().when(productRepository.findById(productDTO.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> {
            productService.getById(productDTO.getId());
        });
    }

    @Test
    void createSuccess() {
        lenient().when(modelMapper.map(any(), any())).thenReturn(product).thenReturn(productDTO);
        lenient().when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO productDTO1 = productService.createProduct(productDTO);
        Assertions.assertEquals(productDTO1, productDTO);
    }

    @Test
    void createFail() {
        lenient().when(productRepository.findByName(productDTO.getName())).thenReturn(product);
        Assertions.assertThrows(DuplicateKeyException.class, () -> {
            productService.createProduct(productDTO);
        });
    }

    @Test
    void updateSuccess() throws NotFoundException {
        lenient().when(productRepository.findById(any())).thenReturn(Optional.of(product));
        lenient().when(modelMapper.map(any(), any())).thenReturn(product);
        lenient().when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO productDT = productService.updateProduct(this.productDTO);
        Assertions.assertEquals(productDT.getName(), product.getName());
    }

    @Test
    void updateFail() {
        lenient().when(productRepository.findById(productDTO.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> {
            productService.updateProduct(productDTO);
        });
    }

    @Test
    void delete() throws NotFoundException {
        lenient().when(productRepository.findById(any())).thenReturn(Optional.of(product));
        productService.deleteProduct(1L);
    }

    @Test
    void deleteFail() {
        lenient().when(productRepository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> {
            productService.deleteProduct(9786877L);
        });
    }
}
