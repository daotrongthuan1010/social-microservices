package social.auth.com.authservice.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;


public class ValidationUtils {



    public ValidationUtils(Validator validator) {

    }

    public static <T> Set<ConstraintViolation<T>> validateEntity(T entity) {
        return null;
    }
}