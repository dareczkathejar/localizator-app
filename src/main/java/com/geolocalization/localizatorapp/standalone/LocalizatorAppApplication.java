package com.geolocalization.localizatorapp.standalone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("com.geolocalization.localizatorapp")
public class LocalizatorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalizatorAppApplication.class, args);
	}

}
