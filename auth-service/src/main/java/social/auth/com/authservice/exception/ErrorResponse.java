package social.auth.com.authservice.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    @JsonProperty(namespace = "status_code")
    private final int code;

    @JsonProperty(namespace = "message")
    private final String messageError;
}
