package com.user.ums.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {
	
	
	Info info()
	{
		return new Info().title("User Management System API").version("1.0v").description("User Management System is a RESTFUL API built using Spring Boot and MYSQL Database ").contact(contact());
	}
	
	@Bean
	OpenAPI openAPI()
	{
		return new OpenAPI().info(info());
	}
	Contact contact()
	{
		return new Contact().email("subamraghu11154@gmail.com").name("Raghu");
	}
}
