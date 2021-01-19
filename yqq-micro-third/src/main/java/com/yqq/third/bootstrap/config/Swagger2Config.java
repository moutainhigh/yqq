/*
 * 文 件 名:  Swagger2Config.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2019年2月27日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.third.bootstrap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Value("${xw.accessTokenUri}")
    private String accessTokenUri;
    
    @Value("${xw.env}")
    private String env;
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("约球圈 接口")
                // 文档描述
                .description("Global Member Operating Platform").termsOfServiceUrl("http://www.baidu.cn").version("v1").build();
    }

    @Bean
    public Docket api() {

        List<Parameter> pars = new ArrayList<Parameter>();
        
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("Content-Type").modelRef(new ModelRef("string")).defaultValue("application/json;charset=UTF-8").parameterType("header")
                .required(true).build();
        
        
        if(env.equals("dev")) {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.yqq.third.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .globalOperationParameters(pars);
        }else {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.yqq.third.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .securitySchemes(Arrays.asList(securitySchema()))
                    .securityContexts(Collections.singletonList(securityContext()))
                    .globalOperationParameters(pars);
        }
    }
    
    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
        authorizationScopeList.add(new AuthorizationScope("all", "accessEverything"));
        List<GrantType> grantTypes = new ArrayList<>();
        GrantType grantType = new ClientCredentialsGrant(accessTokenUri);
        grantTypes.add(grantType);
        return new OAuthBuilder().name("oauth2")
                .scopes(authorizationScopeList)
                .grantTypes(grantTypes)
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope("all", "accessEverything");

        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("")
                .clientSecret("")
                .scopeSeparator(",")
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .additionalQueryStringParams(null)
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
      return UiConfigurationBuilder.builder()
          .deepLinking(true)
          .displayOperationId(false)
          .defaultModelsExpandDepth(1)
          .defaultModelExpandDepth(1)
          .defaultModelRendering(ModelRendering.EXAMPLE)
          .displayRequestDuration(false)
          .docExpansion(DocExpansion.NONE)
          .filter(false)
          .maxDisplayedTags(null)
          .operationsSorter(OperationsSorter.ALPHA)
          .showExtensions(false)
          .tagsSorter(TagsSorter.ALPHA)
          .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
          .validatorUrl(null)
          .build();
    }
}
