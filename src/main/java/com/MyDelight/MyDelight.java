package com.MyDelight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RequestMapping("/test")
public class MyDelight extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(MyDelight.class, args);
		
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(MyDelight.class);
	}

	@GetMapping("/get")
	public String getResult(){
		return "Success...";
	}

}
