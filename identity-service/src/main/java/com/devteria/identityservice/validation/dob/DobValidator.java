package com.devteria.identityservice.validation.dob;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.temporal.ChronoUnit;


import java.time.LocalDate;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {
    private int min;

    /* get value from Anotation trong ham khoi tao */
    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.min = constraintAnnotation.min();
    }


    /* process logic de quyet dinh truong duoc danh dau co hop le hay khong*/
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(Objects.isNull(value)) return true;

        long  years  = ChronoUnit.YEARS.between(value, LocalDate.now());

        return years>=min;
    }
}
