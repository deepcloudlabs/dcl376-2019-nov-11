package com.example.imdb.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = {})
@Pattern(regexp = "^tt[0-9]{6,8}$")
public @interface Imdb {
	String message() default "{validation.email}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
