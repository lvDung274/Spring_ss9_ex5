package com.example.demo_department.models.validator.constraint_Validator;

import com.example.demo_department.models.validator.annotation.FileExtension;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;


public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {

    private String[] allowedExtensions;

    @Override
    public void initialize(FileExtension constraintAnnotation) {
        this.allowedExtensions = constraintAnnotation.allowedExtensions();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true;
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return false;
        }

        String lowerCaseName = originalFilename.toLowerCase();

        return Arrays.stream(allowedExtensions).anyMatch(lowerCaseName::endsWith);
    }
}
