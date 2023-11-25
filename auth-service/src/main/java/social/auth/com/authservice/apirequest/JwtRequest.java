package social.auth.com.authservice.apirequest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtRequest {

    private final String email;

    private final  String password;
}
