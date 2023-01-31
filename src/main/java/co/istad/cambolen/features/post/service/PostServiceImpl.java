package co.istad.cambolen.features.post.service;

import org.springframework.stereotype.Service;

import co.istad.cambolen.features.model.ApiResponse;
import co.istad.cambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final WebClientUtils webClientUtils;

    @Override
    public ApiResponse<?> getAllPosts(Long pageNum) {

        ApiResponse<?> response = webClientUtils.fetch(pageNum, "/posts");
        // System.out.println("ServiceGetPost="+response);

        return response;
    }

    @Override
    public ApiResponse<?> getTopDownloadPosts() {
        ApiResponse<?> response = webClientUtils.fetchNoPagination("/posts/top-download");
        // System.out.println("TopDownload_Post="+response);
        return response;
    }

    @Override
    public ApiResponse<?> getPostDetail(Long id) {
        ApiResponse<?> response = webClientUtils.fetchById("/posts",id);
        // System.out.println("TopDownload_Post="+response);
        return response;

    }
    
}
