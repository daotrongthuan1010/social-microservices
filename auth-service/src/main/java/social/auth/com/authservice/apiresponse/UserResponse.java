package social.auth.com.authservice.apiresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    @JsonProperty("username")
    private final String username;
}
