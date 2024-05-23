package dev.reddy.olm.model;


import dev.reddy.olm.enums.Authority;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser {

    private String userId;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String bio;
    private String imageUrl;
    private String role;
    private String authority;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean mfa;
    private boolean enabled;

}
