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
    
    public static void main(String[] args) {	
		SpringApplication.run(SearchApiApplication.class, args);
	}

}
