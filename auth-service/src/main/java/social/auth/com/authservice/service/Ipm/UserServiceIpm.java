package social.auth.com.authservice.service.Ipm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.auth.com.authservice.apirequest.UserRequest;
import social.auth.com.authservice.model.User;
import social.auth.com.authservice.model.security.Role;
import social.auth.com.authservice.model.security.UserRole;
import social.auth.com.authservice.repository.RoleRepository;
import social.auth.com.authservice.repository.UserRepository;
import social.auth.com.authservice.repository.adnet.AddressRepository;
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

    private final AddressRepository addressRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Optional<User> createOrUpdateUser(UserRequest userRequest) {

        return Optional.of(findByUsername(userRequest.getUsername())
                .map(existingUser -> updateUser(UserRequest.builder().build()))
                .orElseGet(() -> createNewUser(UserRequest.builder().build())));
    }


    private User updateUser(UserRequest userRequest) {

        User user = User.builder()
                .password(new BCryptPasswordEncoder().encode(userRequest.getPassword()))
                .imgAvatar(userRequest.getLinkAvatar())
                .address(getAddress(userRequest.getAddress()))
                .numberPhone(userRequest.getNumberPhone())
                .email(userRequest.getEmail())
                .build();

        updateRoles(user, List.of(userRequest.getRole()));

        return userRepository.save(user);
    }

    private User createNewUser(UserRequest userRequest) {

        User newUser = User.builder()
                .username(userRequest.getUsername())
                .password(new BCryptPasswordEncoder().encode(userRequest.getPassword()))
                .address(getAddress(userRequest.getAddress()))
                .imgAvatar(userRequest.getLinkAvatar())
                .email(userRequest.getEmail())
                .build();

        updateRoles(newUser, List.of(userRequest.getRole()));
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

    private String getAddress(String name){
        return addressRepository.findAddress(name).toString();
    }
}