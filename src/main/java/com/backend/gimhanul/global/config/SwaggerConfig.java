package com.backend.gimhanul.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket swaggerAPI() {
    return new Docket(SWAGGER_2)
            .securityContexts(List.of(securityContext()))
            .securitySchemes(List.of(apiKey()))
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(swaggerInfo());
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return List.of(new SecurityReference("Authorization", authorizationScopes));
  }


  private ApiKey apiKey() {
    return new ApiKey("Authorization", "Authorization", "header");
  }

  private ApiInfo swaggerInfo() {
    return new ApiInfoBuilder().title("고운말 서버 API문서")
            .description("고운말 서버의 API문서 입니다")
            .license("고운말")
            .licenseUrl("https://github.com/TEAM-gimhanul/back-end")
            .version("1.0.0")
            .build();
  }
}
