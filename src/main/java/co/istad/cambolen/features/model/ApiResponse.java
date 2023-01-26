package co.istad.cambolen.features.model;

import lombok.Data;

@Data
public class ApiResponse<T>{

    private String status;
    private String code;
    private String message;
    private T data;
}   
