package br.atm.location.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* This class implements the Swagger API to provide documentation to the services inside this package/project
*
* @author Wanessa Nascimento
*/

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	private static final DocumentationType DOCKET_SWAGER_VERSION = DocumentationType.SWAGGER_2;
 
	@Bean
	public Docket detalheApi() {
		Docket docket = new Docket(DOCKET_SWAGER_VERSION);
		return docket
		.select()
		.apis(RequestHandlerSelectors.basePackage("br.atm.location"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build());
	}
 
	private ApiInfoBuilder informacoesApi() {
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title("Lets Movie API")
		.description("Documentation API.")
		.version("1.0").termsOfServiceUrl("")
		.license("Open Source")
		.licenseUrl("")
		.contact(new Contact("Wanessa Nascimento", "", "wanessanasccimento@gmail.com"));
		return apiInfoBuilder;
	}
}