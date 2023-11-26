package social.auth.com.authservice.service.Ipm;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import social.auth.com.authservice.model.User;
import social.auth.com.authservice.model.security.Role;
import social.auth.com.authservice.model.security.UserRole;
import social.auth.com.authservice.repository.RoleRepository;
import social.auth.com.authservice.repository.UserRepository;
import social.auth.com.authservice.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceIpm implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User createUser(String username, String password, String email, List<String> roles) {
        return findByUsername(username)
                .map(existingUser -> updateUser(password, email, roles))
                .orElseGet(() -> createNewUser(username, password, email, roles));
    }

    private User updateUser(String password, String email, List<String> roles) {

        User user = User.builder()
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .build();

        updateRoles(user, roles);

        return userRepository.save(user);
    }

    private User createNewUser(String username, String password, String email, List<String> roles) {
        User newUser = User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .build();

        updateRoles(newUser, roles);
        return userRepository.save(newUser);
    }

    private void updateRoles(User user, List<String> roles) {
        Set<UserRole> userRoles = new HashSet<>();
        for (String roleName : roles) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
            userRoles.add(new UserRole(user, role));
        }
        user.setUserRoles(userRoles);
    }
}