package co.istad.cambolen.features.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.cambolen.features.file.model.File;
import co.istad.cambolen.features.model.ApiResponse;
import co.istad.cambolen.features.post.model.Category;
import co.istad.cambolen.features.post.web.CreatePostDto;
import co.istad.cambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final WebClient webClient;
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
        ApiResponse<?> response = webClientUtils.fetchById("/posts", id);
        // System.out.println("TopDownload_Post="+response);
        return response;

    }

    @Override
    public ApiResponse<?> postUpload(CreatePostDto body, MultipartFile file) throws JsonProcessingException {
        
        body.setDatePublic(true);
        // define photo
        MultipartBodyBuilder filePart = new MultipartBodyBuilder();
        filePart.part("file", file.getResource());
        ApiResponse<File> fileResponse = webClient.post()
                .uri("/files")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(filePart.build()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<File>>() {
                }).block();

        body.setPhotoId(fileResponse.getData().getId());

        // Define posts_categories
        List<Integer> categories = new ArrayList<Integer>();
        categories.add(1);
        body.setCategoriesId(categories);

        // System.out.println("postIMpl=" + body);
        ApiResponse<?> response = webClientUtils.insert("/posts/create", body);
        
        return response;
    }

}
