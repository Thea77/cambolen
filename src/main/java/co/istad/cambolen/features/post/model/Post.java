package co.istad.cambolen.features.post.model;

import java.util.Date;
import java.util.List;

import co.istad.cambolen.features.file.model.File;
import co.istad.cambolen.features.user.User;
import lombok.Data;

@Data
public class Post {
    private Long id;
    private User author;
    private String title;
    private String description;
    private File photo;
    private Date datePublished;
    private Integer like;
    private String location;
    private Boolean isEnabled;

    private List<Category> categories;
}
