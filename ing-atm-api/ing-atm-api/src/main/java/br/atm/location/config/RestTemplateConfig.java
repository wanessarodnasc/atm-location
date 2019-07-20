package br.atm.location.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
* 
* This class provide a instance of RestTemplate. RestTemplate is tread-safe
* 
* @author Wanessa Nascimento
* 
*/
@Configuration
public class RestTemplateConfig {
	
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}