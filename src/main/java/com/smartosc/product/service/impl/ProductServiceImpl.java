package com.smartosc.product.service.impl;

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
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(pro.getId());
            productDTO.setDescription(pro.getDescription());
            productDTO.setName(pro.getName());
            productDTO.setPrice(pro.getPrice());
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product1 = productRepository.findByName(productDTO.getName());
        if (product1 != null) {
            throw new DuplicateKeyException("Sản phẩm đã tồn tại");
        }
        Product product = modelMapper.map(productDTO, Product.class);
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("khong tim thay id" + id);
        }
    }

    @Override
    public ProductDTO getById(Long id) throws NotFoundException {
        Optional<Product> productEntity = productRepository.findById(id);
        ProductDTO productDTO = new ProductDTO();
        if (productEntity.isPresent()) {
            Product products = productEntity.get();
            productDTO.setDescription(products.getDescription());
            productDTO.setId(products.getId());
            productDTO.setName(products.getName());
            productDTO.setPrice(products.getPrice());
            return productDTO;
        } else {
            throw new NotFoundException("Id: " + id + " isn't exist");
        }

    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            return modelMapper.map(productRepository.save(product), ProductDTO.class);
        } else {
            throw new NotFoundException("Product not found");
        }
    }
}
