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
            .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb2t1bnRoZWEiLCJleHAiOjE2NzQ3MTQ4NzAsImlhdCI6MTY3NDY5Njg3MH0.rBrn3GVFVy_Cr3141sncHXfjtrrh545cbwNDoG3aaCaE2RxAt74jQ_VVlKLzJze-cCCJX7GTOdUcRClL--mGLg")
            .build();
    }
  
}
