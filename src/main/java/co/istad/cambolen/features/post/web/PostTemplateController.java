package co.istad.cambolen.features.post.web;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import co.istad.cambolen.config.security.UserDetailsServiceImpl;
import co.istad.cambolen.features.model.ApiResponse;
import co.istad.cambolen.features.post.model.Post;
import co.istad.cambolen.features.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostTemplateController {

    private final PostServiceImpl postServiceImpl;

    // @GetMapping("/")
    // String requestPostTable( ModelMap map){

    //     var response = postServiceImpl.getAllPosts(1L);
    //     System.out.println("dataController="+response);
    //     map.addAttribute("data",response);
    //     return "home/home";
    // }


}   
