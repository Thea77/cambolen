package co.istad.cambolen.features.auth;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.cambolen.features.auth.web.ChangePasswordDto;
import co.istad.cambolen.features.auth.web.CreateUserDto;
import co.istad.cambolen.features.auth.web.EmailConfirmationDto;
import co.istad.cambolen.features.auth.web.ProfileDto;
import co.istad.cambolen.features.auth.web.ResetPasswordDto;
import co.istad.cambolen.features.auth.web.UpdateUserDto;
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
    ApiResponse<?> updateProfileImage(MultipartFile file, ProfileDto body);

     /**
     * edit user information
     * @param body
     * @return
     */
    ApiResponse<?> updateUserprofile(UpdateUserDto body);

     /**
     * change password
     * @param body
     * @return
     */
    ApiResponse<?> changePassword(ChangePasswordDto body);


   /**
    * forgot password
    * @param body
    * @return
 * @throws JsonProcessingException
    */
    ApiResponse<?> forgotPassword(EmailConfirmationDto body) throws JsonProcessingException;


    /**
     * reset password
     * @param body
     * @param email
     * @param token
     * @return
     */
    ApiResponse<?> resetPassword(ResetPasswordDto body, String email, String token);
}
