package social.auth.com.authservice.model.security;

import jakarta.persistence.*;
import lombok.*;
import social.auth.com.authservice.model.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", referencedColumnName="id")
    private Role role;

    public UserRole(User user, Role role) {
        this.role = role;
        this.user = user;
    }
}
