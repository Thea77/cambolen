package co.istad.cambolen.features.file.model;

import lombok.Data;

@Data
public class File {
    private Long id;
    private String uuid;
    private String name;
    private String uri;
    private String extension;
    private Float size;
    private Boolean isEnabled;
    private Integer download;
}
