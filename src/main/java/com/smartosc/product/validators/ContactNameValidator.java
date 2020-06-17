package com.smartosc.product.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * product
 *
 * @author Tung lam
 * @created_at 08/06/2020 - 15:17
 * @created_by Tung lam
 * @since 08/06/2020
 */
public class ContactNameValidator implements ConstraintValidator<ContactNameConstraint, String> {
    @Override
    public void initialize(ContactNameConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String str,
                           ConstraintValidatorContext
                                   constraintValidatorContext) {
        return str != null && str.matches("[a-z]+");
    }
}
