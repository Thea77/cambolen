package co.istad.cambolen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class WebClientConfig {
    
    @Value("${api.base-url}")
    private String apiBaseUrl;


    @Bean
    public WebClient webClient(){

        return WebClient.builder()
            .baseUrl(apiBaseUrl)
            .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlZGl0b3IiLCJleHAiOjE2NzUxNDU2MDYsImlhdCI6MTY3NTEyNzYwNn0.uG1uaXlG1TzekVRGz3ZiJXfuGk676NUnk-I2bOXs9emlKi75D_Apuyayfo15zeC3LrlWoHDXB7SnW6F5T6LRJQ")
            .build();
    }
  
}
