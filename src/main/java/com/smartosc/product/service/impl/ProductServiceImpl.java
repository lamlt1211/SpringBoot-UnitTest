package com.smartosc.product.service.impl;

import com.smartosc.product.convert.ProductConvert;
import com.smartosc.product.dto.ProductDTO;
import com.smartosc.product.entity.Product;
import com.smartosc.product.repository.ProductRepository;
import com.smartosc.product.service.ProductService;
import javassist.NotFoundException;
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
 * @created_at 04/06/2020 - 17:36
 * @created_by Tung lam
 * @since 04/06/2020
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ProductDTO> getAllProduct() throws NotFoundException {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new NotFoundException("không có dữ liệu");
        }
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product pro : products) {
            ProductDTO productDTO = ProductConvert.EntityToDTO(pro);
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product1 = productRepository.findByName(productDTO.getName());
        if (product1 != null) {
            throw new DuplicateKeyException("Sản phẩm đã tồn tại");
        }

        Product product = ProductConvert.DTOToEntity(productDTO);
        try {
            productRepository.save(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productDTO;
    }

    @Override
    public Boolean deleteProduct(Long id) throws NotFoundException {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException("Can't find product with id: " + id);
        }
    }

    @Override
    public ProductDTO getById(Long id) throws NotFoundException {
        Optional<Product> productEntity = productRepository.findById(id);
        ProductDTO productDTO;
        if (productEntity.isPresent()) {
            productDTO = ProductConvert.EntityToDTO(productEntity.get());
            return productDTO;
        } else {
            throw new NotFoundException("Id: " + id + " isn't exist");
        }
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        if (optionalProduct.isPresent()) {
            Product product = modelMapper.map(productDTO, Product.class);
            productRepository.save(product);
            return productDTO;
        } else {
            throw new NotFoundException("Product not found");
        }
    }
}
