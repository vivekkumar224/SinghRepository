package com.hcl.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	public static final String Authorization_HEADER="Authorization";
	
	private ApiKey getApiKey()
	{
		return new ApiKey("JWT", Authorization_HEADER, "heder");
	}
	
	private List<SecurityContext> securityContexts()
	{
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}
	private List<SecurityReference> sf()
	
	{
		AuthorizationScope scope=new AuthorizationScope("global", "accessEveryThing");
		return Arrays.asList( new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}
	
	@Bean 
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).
				apiInfo(getInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(getApiKey()))
				.select().
				apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private static ApiInfo getInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("Blogiing application-Backend", "This project has All the information for blogging", 
				"1.0", "term and service", new Contact("vivek", "https://vivekservice.com", "singh.vivekraj224@gmail.com"), 
				"license of api", "apis urls",Collections.emptyList());
	}

}
