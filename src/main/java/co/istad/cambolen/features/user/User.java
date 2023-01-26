package co.istad.cambolen.features.user;

import java.util.List;

import co.istad.cambolen.features.file.model.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private File profile;
    private Boolean isEnabled;
    private String verificationCode;
    private List<Role> roles;
    private String resetToken;
    private String token;
}
