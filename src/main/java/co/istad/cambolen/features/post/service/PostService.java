package co.istad.cambolen.features.post.service;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.cambolen.features.model.ApiResponse;
import co.istad.cambolen.features.post.web.CreatePostDto;

public interface PostService {
    
    ApiResponse<?> getAllPosts(Long pageNum);

    ApiResponse<?> getTopDownloadPosts();

    ApiResponse<?> getPostDetail(Long id);

    ApiResponse<?> postUpload(CreatePostDto body, MultipartFile file) throws JsonProcessingException;

}
