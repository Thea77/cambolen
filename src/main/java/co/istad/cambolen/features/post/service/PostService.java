package co.istad.cambolen.features.post.service;

import co.istad.cambolen.features.model.ApiResponse;

public interface PostService {
    
    ApiResponse<?> getAllPosts(Long pageNum);

    ApiResponse<?> getTopDownloadPosts();


}
