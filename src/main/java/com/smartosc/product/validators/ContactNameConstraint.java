package com.smartosc.product.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * product
 *
 * @author Tung lam
 * @created_at 08/06/2020 - 15:12
 * @created_by Tung lam
 * @since 08/06/2020
 */
@Documented
@Constraint(validatedBy = ContactNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactNameConstraint {
    String message() default "Vui long nhap ten";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
