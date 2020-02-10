package com.example.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class ImsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsApplication.class, args);
	}

	@Bean
	public Docket createSwaggerConfigurationDocket() {
		return new Docket(DocumentationType.SWAGGER_2.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example"))   //  excludes spring specific stuff
				.build()
				.apiInfo(getApiInfoForSwagger());
	}

	private ApiInfo getApiInfoForSwagger() {
		return new ApiInfo("Title for IMS Project", "Description for IMS Project", "v1.0.0", "https://domain.com/terms-of-use", new Contact("Contact Person Name", "http://domain.com", "contact@domain.com"), "MIT Licence", "https://opensource.org/licenses/MIT", Collections.emptyList());
	}

}
