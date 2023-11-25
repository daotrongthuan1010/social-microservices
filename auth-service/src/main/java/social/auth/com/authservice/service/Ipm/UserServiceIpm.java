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
        Optional<User> opt = userRepository.findById(id);
        return opt;
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
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User createUser(String username, String password, String email, List<String> roles) {
        Optional<User> existingUser = findByUsername(username);

        if (existingUser.isPresent()) {
            // User already exists, update its information
            User user = existingUser.get();
            User.builder().password(new BCryptPasswordEncoder().encode(password))
            .email(email).build();

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
            return userRepository.save(user);
        } else {
            // User does not exist, create a new user
            User newUser = User.builder()
                    .username(username)
                    .password(new BCryptPasswordEncoder().encode(password))
                    .email(email)
                    .build();

            Set<UserRole> userRoles = new HashSet<>();
            for (String roleName : roles) {
                Role role = roleRepository.findByName(roleName);
                if (role == null) {
                    role = new Role();
                    role.setName(roleName);
                    roleRepository.save(role);
                }
                userRoles.add(new UserRole(newUser, role));
            }

            newUser.setUserRoles(userRoles);
            return userRepository.save(newUser);
        }
    }
}
