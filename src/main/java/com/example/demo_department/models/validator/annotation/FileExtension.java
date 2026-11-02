package com.example.demo_department.models.validator.annotation;

import com.example.demo_department.models.validator.constraint_Validator.FileExtensionValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileExtensionValidator.class)
@Documented
public @interface FileExtension {
    String message() default "Invalid file extension";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] allowedExtensions() default {".jpeg", ".jpg", ".png", ".gif", ".bmp"};
}
