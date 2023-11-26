package social.auth.com.authservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import social.auth.com.authservice.apirequest.UserRequest;
import social.auth.com.authservice.model.User;
import social.auth.com.authservice.service.UserSecurityService;
import social.auth.com.authservice.service.UserService;
import social.auth.com.authservice.utils.ContentMessageConstant;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserSecurityService userSecurityService;

    @PostMapping("/create")
    public ResponseEntity<String> index(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail(),  List.of(userRequest.getRole()));
        return new ResponseEntity<>(ContentMessageConstant.CREATE_USER_SUCCESS, HttpStatus.CREATED);
    }
}
