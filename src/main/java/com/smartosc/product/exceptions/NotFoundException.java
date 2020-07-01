package com.smartosc.product.exceptions;

/**
 * product
 *
 * @author Tung lam
 * @created_at 29/06/2020 - 11:54
 * @created_by Tung lam
 * @since 29/06/2020
 */
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}
