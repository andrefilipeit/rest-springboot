package br.com.rest.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

//Swagger configuration
//for more: https://lankydan.dev/documenting-a-spring-rest-api-following-the-openapi-specification
@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("RESTful API with Java 18 and Spring Boot 3")
				.version("v1")
				.description("RESTful API developed by André Filipe")
				//.termsOfService("https://www.linkedin.com/in/andrefilipeos/")
				.license(new License().name("Apache 2.0").url("https://www.linkedin.com/in/andrefilipeos/"))
				.contact(new Contact().name("Contact").url("https://www.linkedin.com/in/andrefilipeos/"))
				);
	}

}
