package com.smartosc.product.dto;

import com.smartosc.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * product
 *
 * @author Tung lam
 * @created_at 22/06/2020 - 10:46
 * @created_by Tung lam
 * @since 22/06/2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;

    private List<ProductDTO> products;

}
