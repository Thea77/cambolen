package co.istad.cambolen.features.auth.web;

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
public class UpdateUserDto {
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private String email;
}
