package co.istad.cambolen.features.post.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreatePostDto {
    private String title;
    private String description;
    private Long photoId;
    private Boolean datePublic;
    private String location;
    private List<Integer> categoriesId;
}
