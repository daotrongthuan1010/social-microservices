package social.auth.com.authservice.apirequest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {

    private final String username;

    private final String password;

    private final String email;

    private final String role;

}
