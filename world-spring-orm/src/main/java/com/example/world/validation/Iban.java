package com.example.world.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IbanValidator.class)
public @interface Iban {
	String message() default "{validation.iban}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
