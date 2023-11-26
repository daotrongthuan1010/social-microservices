package social.auth.com.authservice.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class MaxValidator implements ConstraintValidator<Max, String> {

    private long maxLong;

    @Override
    public void initialize(Max constraintAnnotation) {
        this.maxLong = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.isEmpty()){
            return true;
        }
        return Long.parseLong(value) <= maxLong;
    }


}
