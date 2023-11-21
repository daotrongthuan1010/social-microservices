package social.auth.com.authservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import social.auth.com.authservice.model.User;

public interface UserService  {

    UserDetails getUserByUsername(String username);

}
