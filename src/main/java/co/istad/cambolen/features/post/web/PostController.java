package co.istad.cambolen.features.post.web;

import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.cambolen.features.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostServiceImpl postServiceImpl;

    @GetMapping("/")
    String requestPostTable(ModelMap map) {

        var getAllPost = postServiceImpl.getAllPosts(1L);
        var getTopDownloadPost = postServiceImpl.getTopDownloadPosts();

        // System.out.println("dataController="+getTopDownloadPost);
        map.addAttribute("topDownload", getTopDownloadPost);
        map.addAttribute("data", getAllPost);

        return "home/home";
    }

    @GetMapping("/details/{id}")
    String postDetail(@PathVariable("id") Long id, ModelMap map) {
        // if (id != null) {
            var details = postServiceImpl.getPostDetail(id);
            System.out.println("detail=" + id);
            map.addAttribute("data", details);

        // }
        return "post/detail";
    }


    @GetMapping("/upload")
    String postUpload(Model model) {
        model.addAttribute("body", new CreatePostDto());
        return "post/upload";
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String doPostUpload(@ModelAttribute("body") CreatePostDto body, 
                        @PathParam("file") MultipartFile file) throws JsonProcessingException {

        // System.out.println("post="+body);

        if(body != null){
            postServiceImpl.postUpload(body,file);
            return "redirect:/";
        }
        return "redirect:/upload";
    }
}
