package br.com.rest.springboot.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");
	
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		converters.add(new YamlJackson2HttpMessageConverter());
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		
		//see more https://www.baeldung.com/spring-mvc-content-negotiation-json-xml
		
		//Via EXTENSION. http://localhost:8080/api/person/v1.xml DEPRECATED on SpringBoot 2.0
		
		//Via QUERY PARAM. http://localhost:8080/api/person/v1?mediaType=xml
		
		/** Content Negotiation VIA Query PARAM
		 * 
	    configurer.favorParameter(true).
	    parameterName("mediaType").
	    ignoreAcceptHeader(true).
	    useRegisteredExtensionsOnly(false).
	    defaultContentType(MediaType.APPLICATION_JSON).
	    mediaType("xml", MediaType.APPLICATION_XML). 
	    mediaType("json", MediaType.APPLICATION_JSON);  
	    */
		
		
		/**
		 *  Content Negotiation VIA HEADER
		 *  HEADER
		 *  Key: Accept  |  Value: application/json || application/xml
		 */
	    configurer.favorParameter(false).
	    parameterName("mediaType").
	    ignoreAcceptHeader(false).
	    useRegisteredExtensionsOnly(false).
	    defaultContentType(MediaType.APPLICATION_JSON).
	    mediaType("xml", MediaType.APPLICATION_XML). 
	    mediaType("json", MediaType.APPLICATION_JSON).
	    mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);  
	}
	
}
