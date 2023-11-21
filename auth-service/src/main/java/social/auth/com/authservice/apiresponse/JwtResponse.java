package social.auth.com.authservice.apiresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class JwtResponse {

    @JsonProperty(namespace = "token")
    private final String jwtToken;

    @JsonProperty(namespace = "username")
    private final String username;
}
