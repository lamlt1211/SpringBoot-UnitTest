package com.smartosc.product.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * product
 *
 * @author Tung lam
 * @created_at 08/06/2020 - 15:56
 * @created_by Tung lam
 * @since 08/06/2020
 */
@Documented
@Constraint(validatedBy = ContactDescriptionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactDescriptionConstraint {
    String message() default "Vui long nhap mo ta";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
