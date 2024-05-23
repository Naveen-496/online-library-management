package dev.reddy.olm.dto;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String role;
    private String phone;
}
