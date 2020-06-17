package com.smartosc.product.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * product
 *
 * @author Tung lam
 * @created_at 08/06/2020 - 15:01
 * @created_by Tung lam
 * @since 08/06/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject {
    private HttpStatus status;
    private Map<String, String> message;
}
