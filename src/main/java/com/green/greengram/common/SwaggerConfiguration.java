package com.green.greengram.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "그린그램",
                description = "GreenGram with react",
                version = "v3"
        ),
//        End Point 마다 자물쇠 아이콘 생성 ( 로그인 가능 )
        security = @SecurityRequirement(name = "authorization")
)

// JWT 토큰을 swagger 에서 사용하게 만드는 코드
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "authorization",
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
public class SwaggerConfiguration {

}
