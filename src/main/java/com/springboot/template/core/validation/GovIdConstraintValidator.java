package com.springboot.template.core.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GovIdConstraintValidator implements ConstraintValidator<GovId, String> {

    private boolean NULLABLE;

    @Override
    public void initialize(GovId id) {
        NULLABLE = id.nullable();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext theConstraintValidatorContext) {

        return this.checkGovID(value, this.NULLABLE);
    }

    /**
     * https://www.simlict.com/tcno.html
     */
    private Boolean checkGovID(String input, boolean nullable) {

        boolean valid = false;

        if (nullable && input == null) return true;
        else if (input != null && input.length() == 11 && StringUtils.isNumeric(input)) {

            long digit10 = Character.getNumericValue(input.charAt(input.length() - 2));
            long digit11 = Character.getNumericValue(input.charAt(input.length() - 1));

            long calcDigit10 = Math.floorMod(((
                    Character.getNumericValue(input.charAt(0)) +
                            Character.getNumericValue(input.charAt(2)) +
                            Character.getNumericValue(input.charAt(4)) +
                            Character.getNumericValue(input.charAt(6)) +
                            Character.getNumericValue(input.charAt(8))
            ) * 7 - (
                    Character.getNumericValue(input.charAt(1)) +
                            Character.getNumericValue(input.charAt(3)) +
                            Character.getNumericValue(input.charAt(5)) +
                            Character.getNumericValue(input.charAt(7))
            )), 10);

            long calcDigit11 = (
                    Character.getNumericValue(input.charAt(0)) +
                            Character.getNumericValue(input.charAt(1)) +
                            Character.getNumericValue(input.charAt(2)) +
                            Character.getNumericValue(input.charAt(3)) +
                            Character.getNumericValue(input.charAt(4)) +
                            Character.getNumericValue(input.charAt(5)) +
                            Character.getNumericValue(input.charAt(6)) +
                            Character.getNumericValue(input.charAt(7)) +
                            Character.getNumericValue(input.charAt(8)) +
                            calcDigit10
            ) % 10;

            valid = (digit10 == calcDigit10) && (digit11 == calcDigit11);
        }

        return valid;
    }

}
