package co.istad.cambolen.features.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.istad.cambolen.config.security.CustomUserSecurity;
import co.istad.cambolen.features.auth.web.ChangePasswordDto;
import co.istad.cambolen.features.auth.web.CreateUserDto;
import co.istad.cambolen.features.auth.web.EmailConfirmationDto;
import co.istad.cambolen.features.auth.web.LoginDto;
import co.istad.cambolen.features.auth.web.ProfileDto;
import co.istad.cambolen.features.auth.web.ResetPasswordDto;
import co.istad.cambolen.features.auth.web.UpdateUserDto;
import co.istad.cambolen.features.file.model.File;
import co.istad.cambolen.features.model.ApiResponse;
import co.istad.cambolen.features.user.User;
import co.istad.cambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final WebClient webClient;
    private final WebClientUtils webClientUtils;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    public ApiResponse<?> loginUser(String username, String password) throws JsonProcessingException {

        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);

        System.out.println("bodyLogin=" + loginDto);
        // get User data
        ApiResponse<LoginResponse> response = webClientUtils.login("/auth/login", loginDto);
        System.out.println("Res=" + response);

        LoginResponse userResponse = response.getData();
        // System.out.println("Res2="+ userResponse);

        userResponse.setToken(response.getData().getToken());

        User user = userResponse.getUser();
        user.setToken(userResponse.getToken());
        System.out.println("User=" + user);

        Authentication auth = daoAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password));
        CustomUserSecurity customUserSecurity = (CustomUserSecurity) auth.getPrincipal();

        customUserSecurity.getUser().setToken(user.getToken());

        log.info("Aut={}", customUserSecurity.getUser());

        return response;
    }

    @Override
    public ApiResponse<?> userRegister(CreateUserDto body, MultipartFile file) throws JsonProcessingException {
        MultipartBodyBuilder filePart = new MultipartBodyBuilder();
        filePart.part("file", file.getResource());
        List<Integer> role = new ArrayList<Integer>();
        role.add(2);
        body.setRoleIds(role);

        if (body.getProfileId() == null) {
            body.setProfileId(1L);
            // System.out.println("ImplBodyNull=" + body);

        } else {
            ApiResponse<File> fileResponse = webClient.post()
                    .uri("/files")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(filePart.build()))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<ApiResponse<File>>() {
                    }).block();
            body.setProfileId(fileResponse.getData().getId());

        }
        // System.out.println("ImplBody=" + body);
        ApiResponse<CreateUserDto> response = webClientUtils.insert("/auth/register", body);
        // send confirmation email
        webClientUtils.insert("/auth/send-email-confirmation", body.getEmail());

        return response;
    }

    @Override
    public ApiResponse<?> updateProfileImage(MultipartFile file, ProfileDto body) {
        MultipartBodyBuilder filePart = new MultipartBodyBuilder();
        filePart.part("file", file.getResource());

        ApiResponse<File> fileResponse = webClient.post()
                .uri("/files")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(filePart.build()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<File>>() {
                }).block();
        body.setProfileId(fileResponse.getData().getId());

        ApiResponse<ProfileDto> updateProfile = webClient.put()
                .uri("/auth/change-profile")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ProfileDto>>() {
                }).block();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity userSecurity = (CustomUserSecurity) auth.getPrincipal();
        userSecurity.getUser().setProfile(fileResponse.getData());

        // System.out.println("fileUriUserSecurity="+userSecurity.getUser());

        return updateProfile;
    }

    @Override
    public ApiResponse<?> updateUserprofile(UpdateUserDto body) {
        if (body != null) {
            ApiResponse<?> response = webClientUtils.updateUserprofile("/edit-profile", body);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUserSecurity userSecurity = (CustomUserSecurity) auth.getPrincipal();
            userSecurity.getUser().setFamilyName(body.getFamilyName());
            userSecurity.getUser().setGivenName(body.getGivenName());
            userSecurity.getUser().setPhoneNumber(body.getPhoneNumber());
            userSecurity.getUser().setEmail(body.getEmail());

            // System.out.println("serviceUpdateProfile="+response);
            return response;
        }
        return null;
    }

    @Override
    public ApiResponse<?> changePassword(ChangePasswordDto body) {
        ApiResponse<ChangePasswordDto> response = webClient.put()
                .uri("/auth/change-password")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ChangePasswordDto>>() {
                }).block();

        return response;
    }

    @Override
    public ApiResponse<?> forgotPassword(EmailConfirmationDto body) throws JsonProcessingException {
        if (body.getValue() != null) {
            ApiResponse<EmailConfirmationDto> response = webClientUtils.insert("/auth/forgot-password", body);
            return response;
        }
        return null;
    }

    @Override
    public ApiResponse<?> resetPassword(ResetPasswordDto body, String email, String token) {
        ApiResponse<ResetPasswordDto> response = webClient.put()
        .uri(uriBuilder -> uriBuilder
        .path("/auth/reset-password")
        .queryParam("email", email)
            .queryParam("token", token)
        .build())
        .bodyValue(body)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<ApiResponse<ResetPasswordDto>>() {
        }).block();

        return response;
    }
}
