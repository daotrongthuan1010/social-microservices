package social.auth.com.authservice.service;

import social.auth.com.authservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    void save(User user);

    User createUser(String username, String email,  String password, List<String> roles);


}
