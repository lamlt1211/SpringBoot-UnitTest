package com.smartosc.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * product
 *
 * @author Tung lam
 * @created_at 04/06/2020 - 17:37
 * @created_by Tung lam
 * @since 04/06/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
    private int status;
    private String message;
    private T data;
}
