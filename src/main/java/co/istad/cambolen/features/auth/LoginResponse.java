package co.istad.cambolen.features.auth;

import java.util.List;

import co.istad.cambolen.features.user.Role;
import co.istad.cambolen.features.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class LoginResponse {

    private User user;
    private List<Role> roles;
    private String token;
    
}
