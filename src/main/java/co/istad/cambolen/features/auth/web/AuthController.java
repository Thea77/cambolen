package co.istad.cambolen.features.auth.web;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.cambolen.config.security.CustomUserSecurity;
import co.istad.cambolen.features.auth.AuthServiceImpl;
import co.istad.cambolen.features.model.ApiResponse;
import co.istad.cambolen.features.user.User;
import co.istad.cambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authServiceImpl;
    private final WebClientUtils clientUtils;

    @GetMapping("/login")
    String requestLoginView() {
        return "auth/login";
    }

    @PostMapping("/login")
    String doLogin(@RequestParam("username") String username,
            @RequestParam("password") String password) throws JsonProcessingException {

        authServiceImpl.loginUser(username, password);

        // log.info("controllerRes= {}", response);

        return "redirect:/";
    }

    /**
     * register User(Get)
     * 
     * @param map
     * @param userDto
     * @return
     */
    @GetMapping("/register")
    public String creteUser(Model map) {
        map.addAttribute("userDto", new CreateUserDto());

        return ("auth/register");
    }

    /**
     * Do Create User
     * 
     * @param body
     * @param file
     * @param redirAttrs
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String doCreteUser(@Valid @ModelAttribute("userDto") CreateUserDto userDto,
            BindingResult result, @PathParam("file") MultipartFile file,
            RedirectAttributes redirAttrs,
            Model model) throws JsonProcessingException{

        if (result.hasErrors()) {
            System.out.println("Password=" + model.asMap());
            return ("auth/register");
        } else {
            // System.out.println("no error");
            redirAttrs.addFlashAttribute("isSucceed", "CREATED");
            authServiceImpl.userRegister(userDto, file);
            return ("redirect:/verify-email");
        }
    }


    @GetMapping("/verify-email")
    public String sendEmailConfirmation(Model map, @ModelAttribute("isSucceed") String isSucceed) {
        map.addAttribute("emailDto", new EmailConfirmationDto());
        map.addAttribute("isSucceed", isSucceed);
        return ("auth/verify-email");
    }

    
    
    @PostMapping("/verify-email")
    String doSendEmailConfirmation(@ModelAttribute("emailDto") EmailConfirmationDto emailDto,RedirectAttributes redirAttrs) throws JsonProcessingException{

        redirAttrs.addFlashAttribute("isSucceed", "sent");

        clientUtils.insert("/auth/send-email-confirmation", emailDto.getValue());
    
        return ("redirect:/verify-email");
    }


    @GetMapping("/users-profile")
    String usersProfile(ModelMap map, @ModelAttribute("isSucceed") String isSucceed) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity userSecurity = (CustomUserSecurity) auth.getPrincipal();

        var response = userSecurity.getUser();
        System.out.println("aut="+response);

        map.addAttribute("data",response);
        return "auth/profile";
    }

    @GetMapping("/users-edit-profile")
    String usersEditProfile(ModelMap map, UpdateUserDto userDto, @ModelAttribute("isSucceed") String isSucceed) {

        map.addAttribute("userDto",userDto);
        return "auth/edit-profile";
    }


    @PostMapping(value = "/users-change-profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String doUpdateUserProfileImage(@PathParam("file") MultipartFile file, Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity userSecurity = (CustomUserSecurity) auth.getPrincipal();
        id = userSecurity.getUser().getProfile().getId();
        // System.out.println("ImageId="+id);
        authServiceImpl.updateProfileImage(file, id);

        return "redirect:/users-profile";
    }
}