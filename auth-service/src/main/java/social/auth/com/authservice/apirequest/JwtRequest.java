package social.auth.com.authservice.apirequest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtRequest {

    private final String username;

    private final String password;

}
