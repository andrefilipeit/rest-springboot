package br.com.rest.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	//http://localhost:8080/v3/api-docs
	//http://localhost:8080/swagger-ui/index.html
	
	//for more: https://www.baeldung.com/spring-rest-openapi-documentation
	//https://lankydan.dev/documenting-a-spring-rest-api-following-the-openapi-specification
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("")
						.description("Project developed by Andre Filipe")
						.version("v1")
						.termsOfService("")
						.license(new License().name("AFOS")
								.url("https://www.linkedin.com/in/andrefilipeos/")));
								
	}
	
}
