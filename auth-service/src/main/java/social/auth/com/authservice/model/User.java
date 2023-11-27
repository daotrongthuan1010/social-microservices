package social.auth.com.authservice.model;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;
import social.auth.com.authservice.model.security.UserRole;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false, updatable=false)
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private  String imgAvatar;

    @Transient
    private String confirmPassword;

    private String numberPhone;

    private String address;

    @Transient
    private MultipartFile file;

    @Transient
    private String passwordConfirm;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    public User() {

    }

    public void setUserRoles(Set<UserRole> userRoles) {
    }
}
