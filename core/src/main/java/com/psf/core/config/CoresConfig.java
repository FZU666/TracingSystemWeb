package com.psf.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author psf
 */
@Configuration
public class CoresConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer coresConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").
                    allowedOrigins("*"). // 允许跨域的域名，可以用 * 表示允许任何域名使用
                    allowedMethods("*"). // 允许任何方法（post、get 等）
                    allowedHeaders("*"). // 允许任何请求头
                    allowCredentials(true). // 带上 cookie 信息
                    exposedHeaders(HttpHeaders.SET_COOKIE)
                    .maxAge(3600L); //maxAge (3600) 表明在 3600 秒内，不需要再发送预检验请求，可以缓存该结果
            }
        };
    }
}
