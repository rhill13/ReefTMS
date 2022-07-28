package io.rookware.reefTMSCore.config;

import io.rookware.reefTMSCore.interceptor.AuthenticationServiceInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationServiceInterceptorAppConfig implements WebMvcConfigurer {

    private AuthenticationServiceInterceptor authenticationServiceInterceptor = new AuthenticationServiceInterceptor();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationServiceInterceptor);
    }
}
