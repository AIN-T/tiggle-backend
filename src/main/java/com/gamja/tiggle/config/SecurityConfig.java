package com.gamja.tiggle.config;

import com.gamja.tiggle.config.filter.JwtFilter;
import com.gamja.tiggle.config.filter.LoginFilter;
import com.gamja.tiggle.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((auth) -> auth.disable());
        http.httpBasic((auth) -> auth.disable());
        http.formLogin((auth) -> auth.disable());
        http.sessionManagement((auth) -> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests((auth) ->
                auth
                        .requestMatchers("swagger-ui/**",
                                "user/**", "login/**","program/**","times/**","price/**",
                                "swagger-ui.html/**",
                                "v3/api-docs/**",
                                "v2/api-docs/**",
                                "swagger-resources/**",
                                "swagger-ui/**",
                                "swagger/**", "auth/**").permitAll()
                        .anyRequest().authenticated()
        );
        http.logout((auth) -> auth.deleteCookies("AToken").logoutSuccessUrl("http://localhost:8081/"));

        http.addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);
        http.addFilterAt(new LoginFilter(jwtUtil, authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://127.0.0.1");
        config.addAllowedOrigin("http://localhost:8081");
        config.addAllowedOrigin("http://localhost:8082");
        config.addAllowedOrigin("http://www.tiggle.kro.kr.s3-website.ap-northeast-2.amazonaws.com");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


}