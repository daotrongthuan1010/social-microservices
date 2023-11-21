package social.auth.com.authservice.service.Ipm;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import social.auth.com.authservice.model.User;
import social.auth.com.authservice.model.security.Authority;
import social.auth.com.authservice.model.security.Role;
import social.auth.com.authservice.model.security.UserRole;
import social.auth.com.authservice.repository.UserRepository;
import social.auth.com.authservice.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceIpm implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        return optionalUser.map(user -> {
            Set<GrantedAuthority> authorities = new HashSet<>();

            for (UserRole userRole : user.getUserRoles()) {
                Role role = userRole.getRole();
                authorities.add(new Authority(role.getName()));
            }

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(authorities)
                    .build();
        }).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
