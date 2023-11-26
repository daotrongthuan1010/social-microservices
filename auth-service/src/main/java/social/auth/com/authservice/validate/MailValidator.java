package social.auth.com.authservice.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class MailValidator implements ConstraintValidator<Mail, String> {

    private final static Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;

        if (StringUtils.hasLength(value)) {
            result = pattern.matcher(value).matches();
        }
        return result;
    }
}