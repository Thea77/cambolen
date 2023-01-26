package co.istad.cambolen.features.auth;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.cambolen.features.auth.web.CreateUserDto;
import co.istad.cambolen.features.model.ApiResponse;

public interface AuthService {

    ApiResponse<?> loginUser(String username, String password) throws JsonProcessingException;    
    
    ApiResponse<?> userRegister(CreateUserDto body,MultipartFile file) throws JsonProcessingException;    

     /**
     * change user profile picture
     * @param file
     * @param id
     * @return
     */
    ApiResponse<?> updateProfileImage(MultipartFile file, Long id);

}
