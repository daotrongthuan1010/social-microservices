package social.auth.com.authservice.service;

import social.auth.com.authservice.apirequest.UserRequest;
import social.auth.com.authservice.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);


    Optional<User> createOrUpdateUser(UserRequest userRequest);


}
