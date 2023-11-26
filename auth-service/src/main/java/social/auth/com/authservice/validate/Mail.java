package social.auth.com.authservice.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {MailValidator.class})
public @interface Mail {

    String message() default "{response.jakarta.validation.constraints.Mail}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}