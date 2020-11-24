package com.pd.valid.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pd.common.util.StringCal;

public class MailValidImpl implements ConstraintValidator<MailValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringCal.isMail(value);
    }

}
