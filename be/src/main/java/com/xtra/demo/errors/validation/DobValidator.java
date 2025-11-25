package com.xtra.demo.errors.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DobValidator implements ConstraintValidator<ValidDob, String> {

    private final String DOB_PATTERN = "YYYY-MM-DD";

    @Override
    public void initialize(ValidDob constraintAnnotation) {

    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {

        SimpleDateFormat sdf = new SimpleDateFormat(DOB_PATTERN);
        try {
            sdf.parse(date);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
