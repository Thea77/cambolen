package co.istad.cambolen.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.istad.cambolen.features.user.User;
import co.istad.cambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final WebClientUtils webClientUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     
        User user = webClientUtils.getUserForAuthentication(username).getData();

        CustomUserSecurity customUserSecurity = new CustomUserSecurity();
        customUserSecurity.setUser(user);
   
        // log.info("myUser= {}",customUserSecurity.getUser());
        
        return customUserSecurity;
    }
    
}
