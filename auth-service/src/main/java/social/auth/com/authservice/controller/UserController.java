package social.auth.com.authservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import social.auth.com.authservice.model.User;
import social.auth.com.authservice.service.UserSecurityService;
import social.auth.com.authservice.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserSecurityService userSecurityService;


    @GetMapping("/my-profile")
    public String myProfile(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "myProfile";
    }

    @PostMapping(value="/new-user")
    public String newUserPost(@ModelAttribute("user") User user,
                              @ModelAttribute("new-password") String password,
                              RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getUsername());
        boolean invalidFields = false;

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            redirectAttributes.addFlashAttribute("usernameExists", true);
            invalidFields = true;
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("emailExists", true);
            invalidFields = true;
        }
        if (invalidFields) {
            return "redirect:/login";
        }
        user = userService.createUser(user.getUsername(), password,  user.getEmail(), List.of("ROLE_USER"));
        return "redirect:/my-profile";
    }

    @PostMapping(value="/update-user-info")
    public String updateUserInfo( @ModelAttribute("user") User user,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  @RequestParam("file") MultipartFile file,
                                  Model model, Principal principal) throws Exception {
        Optional<User> currentUser = userService.findByUsername(principal.getName());
        if(currentUser.isPresent()) {
            throw new Exception ("User not found");
        }

        Optional<User> existingUser = userService.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            existingUser.get();
            currentUser.get();
        }

        existingUser = Optional.ofNullable(userService.findByEmail(user.getEmail()));
        if (existingUser.isPresent() && !existingUser.get().getId().equals(currentUser.get().getId()))  {
            model.addAttribute("emailExists", true);
            return "myProfile";
        }

        String passwordHash = null;
        if (newPassword != null && !newPassword.isEmpty() && confirmPassword.equals(newPassword)){

            String dbPassword = currentUser.get().getPassword();
            if(new BCryptPasswordEncoder().matches(user.getPassword(), dbPassword)){
               passwordHash = new BCryptPasswordEncoder().encode(newPassword);
            } else {
                model.addAttribute("incorrectPassword", true);
                return "myProfile";
            }

        }

        userService.save(User.builder().password(passwordHash)
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName()).build());
        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUser);
        userSecurityService.authenticateUser(currentUser.get().getUsername());
        return "myProfile";
    }
}
