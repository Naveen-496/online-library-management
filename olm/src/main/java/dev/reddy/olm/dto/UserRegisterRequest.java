package dev.reddy.olm.dto;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String bio;
}
