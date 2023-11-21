package social.auth.com.authservice.model.security;

import jakarta.persistence.*;
import lombok.Getter;
import social.auth.com.authservice.model.User;

@Entity
@Getter
@Table(name="user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role role;
}
