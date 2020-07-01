package com.smartosc.product;

import com.smartosc.product.dto.CategoryDTO;
import com.smartosc.product.dto.ProductDTO;
import com.smartosc.product.entity.Category;
import com.smartosc.product.entity.Product;
import com.smartosc.product.exceptions.NotFoundException;
import com.smartosc.product.repository.CategoryRepository;
import com.smartosc.product.service.CategoryService;
import com.smartosc.product.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;


/**
 * product
 *
 * @author Tung lam
 * @created_at 26/06/2020 - 10:01
 * @created_by Tung lam
 * @since 26/06/2020
 */
@ExtendWith(SpringExtension.class)
class TestCategoryService {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryService categoryService = new CategoryServiceImpl();

    private Category category;

    private Product product;

    private CategoryDTO categoryDTO;

    private List<Product> productList;

    private List<ProductDTO> productDTOS;

    private List<CategoryDTO> categoryDTOS;

    private List<Category> categoryList;

    @BeforeEach
    public void init() {
        productList = new ArrayList<>();
        category = new Category(1L, "category", "emak", productList);
        categoryList = new ArrayList<>();
        categoryList.add(category);

        productDTOS = new ArrayList<>();
        categoryDTO = new CategoryDTO(1L, "category", "emak", productDTOS);


        product = new Product(1L, "3", new BigDecimal(4), "6", categoryList);
        productList = new ArrayList<>();
        productList.add(product);
    }

    @Test
    void getAllCategorySuccess() {
        lenient().when(categoryRepository.findAll()).thenReturn(categoryList); //
        List<CategoryDTO> c = categoryService.getAllCategory();
        Assertions.assertEquals(c.size(), categoryList.size());
    }

    @Test
    void getAllCategoryFail() {
        lenient().when(categoryRepository.findAll()).thenThrow(com.smartosc.product.exceptions.NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> {
            categoryService.getAllCategory();
        });
    }

    @Test
    void getCategoryByIdSuccess() throws NotFoundException, javassist.NotFoundException {
        lenient().when(categoryRepository.findById(category.getId())).thenReturn(Optional.ofNullable(category));
        CategoryDTO dto = categoryService.getById(categoryDTO.getId());
        Assertions.assertEquals(dto.getId(), product.getId());

    }

    @Test
    void getCategoryByIdFail() {
        lenient().when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> {
            categoryService.getById(categoryDTO.getId());
        });
    }

    @Test
    void createCategorySuccess() {
        lenient().when(modelMapper.map(any(), any())).thenReturn(category).thenReturn(categoryDTO);
        lenient().when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDTO categoryDTO1 = categoryService.createCategory(categoryDTO);
        Assertions.assertEquals(categoryDTO1, categoryDTO);
    }

    @Test
    void createCategoryFail() {
        lenient().when(categoryRepository.findByName(categoryDTO.getName())).thenReturn(category);
        Assertions.assertThrows(DuplicateKeyException.class, () -> {
            categoryService.createCategory(categoryDTO);
        });
    }

    @Test
    void updateCategorySuccess() throws NotFoundException, javassist.NotFoundException {
        lenient().when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        lenient().when(modelMapper.map(any(), any())).thenReturn(category);
        lenient().when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDTO c = categoryService.update(this.categoryDTO);
        Assertions.assertEquals(c.getName(), category.getName());
    }

    @Test
    void updateCategoryFail() {
        lenient().when(categoryRepository.findById(categoryDTO.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> {
            categoryService.update(categoryDTO);
        });
    }
}
