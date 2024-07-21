package com.gamja.tiggle.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {

    private Info info() {
        return new Info()
                .title("TIGGLE")
                .description("우측 Authorization에 [Bearer 토큰값] 을 입력하면 토큰 로그인 인증이 가능합니다.")
                .version("1.0.0");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(new Components()
                        .addSecuritySchemes("Authorization", apiKey()))
                .info(info());
    }

    private SecurityScheme apiKey() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .name("Authorization")
                .in(SecurityScheme.In.HEADER);
    }

}
