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
            .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjc1Nzc0NDgxLCJpYXQiOjE2NzU3NTY0ODF9.ZSrrf2v7yBJcppuUgjQaJuNrwBNdsSoQiX_LI1OKBhrQ2_dUeWz-XJkkeXzwlt4UB1gwrGrvZR1PyW9MQk7Caw")
            .build();
    }
  
}
