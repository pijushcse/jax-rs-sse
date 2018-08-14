package com.pilab.xyz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.pilab.xyz")
public class DemoSseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSseApplication.class, args);
	}
}
