package com.smartosc.product.service;

import com.smartosc.product.dto.ProductDTO;
import javassist.NotFoundException;

import java.util.List;

/**
 * product
 *
 * @author Tung lam
 * @created_at 04/06/2020 - 17:36
 * @created_by Tung lam
 * @since 04/06/2020
 */
public interface ProductService {
    List<ProductDTO> getAllProduct() throws NotFoundException;

    ProductDTO create(ProductDTO productDTO);

    void delete(Long id) throws NotFoundException;

    ProductDTO getById(Long id) throws NotFoundException;

    ProductDTO update(Long id, ProductDTO productDTO) throws NotFoundException;
    
}
