package co.istad.cambolen.features.auth.web;

import java.util.List;

import javax.validation.constraints.NotBlank;

import co.istad.cambolen.validation.password.Password;
import co.istad.cambolen.validation.password.PasswordMatch;
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
@PasswordMatch(password = "password", confirmedPassword = "confirmedPassword")
public class CreateUserDto {
    @NotBlank
    private String username;
    
    private String email;

    @Password
    private String password;
    
    @Password
    private String confirmedPassword;

    private String familyName;
    private String givenName;
    private String phoneNumber;
    private Long profileId;
    private List<Integer> roleIds;
}
