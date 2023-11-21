package social.auth.com.authservice.model.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

}
