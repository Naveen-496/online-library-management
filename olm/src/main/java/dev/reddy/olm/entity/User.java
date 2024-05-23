package dev.reddy.olm.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonInclude(NON_DEFAULT)
public class User extends Auditable{

    @Column(updatable = false, unique = true, nullable = false)
    private String userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column( unique = true, nullable = false)
    private String email;
    @Column(name = "login_attempts")
    private Integer loginAttempts;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    private String phone;
    private String bio;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "account_non_expired")
    private boolean accountNonExpired;
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;
    private boolean mfa;
    private boolean enabled;


    @ManyToOne(fetch = EAGER, cascade = PERSIST)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "id"
    ), inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id"))
    private Role role;

}
