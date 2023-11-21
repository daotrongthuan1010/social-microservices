package social.auth.com.authservice.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private final int code;

    private final String messageError;
}
