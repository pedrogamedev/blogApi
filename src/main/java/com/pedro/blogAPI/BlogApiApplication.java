package com.pedro.blogAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

}
//to-do
/*
	validate requestParams and requestBody (when there are empty, null or invalid values)
	getAll blogPosts
	filtering
	testing
	openAPI swagger UI implementation
	docker
 */
