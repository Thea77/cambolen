package co.istad.cambolen.features.auth.web;
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
@PasswordMatch(password = "newPassword", confirmedPassword = "confirmedPassword")
public class ResetPasswordDto {
    
    @Password
    @NotBlank
    private String newPassword;

    @Password
    @NotBlank
    private String confirmedPassword;
}
