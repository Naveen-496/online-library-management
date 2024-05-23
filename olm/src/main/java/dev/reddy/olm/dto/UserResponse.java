package dev.reddy.olm.dto;

import lombok.*;



@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String userId;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String bio;
    private String imageUrl;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean mfa;
    private boolean enabled;
}
