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
            .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb2t1bnRoZWEiLCJleHAiOjE2NzU2ODYxNzYsImlhdCI6MTY3NTY2ODE3Nn0.VclmyqnQ_FiYGZedHY42aVtCsk0EkfBf-DJKpBvEem99I6d3cajUoudSr7vJXigy3hau8jbzQm_OnQ9HIDie1g")
            .build();
    }
  
}
