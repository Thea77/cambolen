package co.istad.cambolen.utils;

import co.istad.cambolen.features.auth.LoginResponse;
import co.istad.cambolen.features.auth.web.LoginDto;
import co.istad.cambolen.features.model.ApiResponse;
import co.istad.cambolen.features.user.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class WebClientUtils {

    private final WebClient webClient;


    
    public ApiResponse<LoginResponse> login(String endPoint, LoginDto loginDto) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String jsoString = om.writeValueAsString(loginDto);

        return webClient.post()
                .uri(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(jsoString))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<LoginResponse>>() {
                    
                })
                .block();
    }

    public <T> ApiResponse<T> insert(String endPoint, T body ) throws JsonProcessingException {
        String jsoString = "";
        ObjectMapper om = new ObjectMapper();
        jsoString = om.writeValueAsString(body);

        return webClient.post()
                .uri(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(jsoString))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<T>>() {
                    
                })
                .block();
    }

    

    public <T> ApiResponse<Pagination<T>> fetch(Long pageNum, String endPoint) {
        return webClient.get()
                .uri(builder 
                    -> builder.path(endPoint)
                    .queryParam("pageNum", pageNum)
                    .queryParam("pageSize", 7)
                    .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<Pagination<T>>>() {
                }).block();
    }

    public <T> ApiResponse<T> fetchNoPagination(String endPoint) {
        return webClient.get()
            .uri(endPoint)
            .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<T>>() {
                }).block();
    }

    public <T> ApiResponse<T> fetchById(String endPoint, Long id) {
        return webClient.get()
            .uri(endPoint + "/" + id)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<T>>() {
            }).block();
    }

    public <T> ApiResponse<T> getMe() {
        return webClient.get()
            .uri("/users/me")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<T>>() {
            }).block();
    }


    public ApiResponse<User> getUserForAuthentication(String usernameOrEmail) {
        return webClient.get()
            .uri(builder 
                -> builder.path("/users/find-by-usernameoremail")
            .queryParam("usernameOrEmail", usernameOrEmail)
            .build())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<User>>() {
            }).block();
        }


    public <T> ApiResponse<T> delete(String endPoint, Long id) {
        return webClient.delete()
            .uri(endPoint + "/" + id)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<T>>() {
            }).block();
           
    }

    public <T> ApiResponse<T> update(Long id,String endPoint, T body) {
        
        return webClient.put()
            .uri("/users/"+id+"/"+ endPoint)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<T>>() {
            }).block();
           
    }

    public <T> ApiResponse<T> updateUserprofile(String endPoint, T body) {
        
        return webClient.put()
            .uri("/users/"+ endPoint)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ApiResponse<T>>() {
            }).block();
           
    }

}
