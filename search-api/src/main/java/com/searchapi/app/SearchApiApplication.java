package com.searchapi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ch.qos.logback.core.net.server.Client;

@SpringBootApplication
@RestController
public class SearchApiApplication {

	
    @RequestMapping("/home")
    public String home() {
        return "main page";
    }
    
    @RequestMapping("/pobierzObiekt")
    public String pobierzObiekt() {
        return "zwracam jsonem obiekty";
    }
    
    public static void main(String[] args) {	
		SpringApplication.run(SearchApiApplication.class, args);
	}
    
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }

        };
    }


}
