package com.smartosc.product.dto;

import com.smartosc.product.validators.ContactDescriptionConstraint;
import com.smartosc.product.validators.ContactNameConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;


/**
 * product
 *
 * @author Tung lam
 * @created_at 04/06/2020 - 17:33
 * @created_by Tung lam
 * @since 04/06/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    @ContactNameConstraint
    private String name;

    @NotNull(message = "Please provide a price")
    @DecimalMin(value = "1.00", message = "value must be more 1.00")
    private BigDecimal price;

    @ContactDescriptionConstraint
    private String description;

//    public ProductDTO(Long id, String name, BigDecimal price, String description) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//    }
}
