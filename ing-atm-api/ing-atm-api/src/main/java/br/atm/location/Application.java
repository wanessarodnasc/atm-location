package br.atm.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
* 
* @author Wanessa Nascimento
*
*/
@SpringBootApplication
@EnableCaching
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 
}

