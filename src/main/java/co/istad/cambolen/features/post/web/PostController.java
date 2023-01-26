package co.istad.cambolen.features.post.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import co.istad.cambolen.features.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostServiceImpl postServiceImpl;

    @GetMapping("/")
    String requestPostTable( ModelMap map){

        var getAllPost = postServiceImpl.getAllPosts(1L);
        var getTopDownloadPost = postServiceImpl.getTopDownloadPosts();

        System.out.println("dataController="+getTopDownloadPost);
        map.addAttribute("topDownload",getTopDownloadPost);
        map.addAttribute("data",getAllPost);

        return "home/home";
    }

}
