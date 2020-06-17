package com.smartosc.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * product
 *
 * @author Tung lam
 * @created_at 12/06/2020 - 11:19
 * @created_by Tung lam
 * @since 12/06/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long accountId;
    private String username;
    private String password;
    private Boolean status;
}
