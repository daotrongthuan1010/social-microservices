package social.auth.com.authservice.apirequest;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class UserRequest {

    private final String username;

    private final String password;

    private final MultipartFile avatar;

    private final String address;

    private final String linkAvatar;

    private final String numberPhone;

    private final String email;

    private final String role;

}
