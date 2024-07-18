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

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;
    //private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    //private final OAuth2Service oAuth2Service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((auth) -> auth.disable());
        http.httpBasic((auth) -> auth.disable());
        http.formLogin((auth) -> auth.disable());
        http.sessionManagement((auth) -> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 특정 페이지를 차단하고 나머지 다 허용 - 블랙리스트
        // 특정 페이지를 허용하고 나머지 다 차단 - 화이트리스트

        http.authorizeHttpRequests((auth) ->
                        auth
//                        .requestMatchers(HttpMethod.GET, "//**", "/admin/**").has("ADMIN")
//                        .requestMatchers("/test/**", "/mypage").authenticated()
//                        .requestMatchers("/login", "/member/**", "/kakao/**").permitAll()
//                        .anyRequest().authenticated()

                                .anyRequest().permitAll()
        );

        http.addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);
        http.addFilterAt(new LoginFilter(jwtUtil, authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);
//        http.oauth2Login((config) -> {
//            config.successHandler(oAuth2AuthenticationSuccessHandler);
//            config.userInfoEndpoint((endpoint) -> endpoint.userService(oAuth2Service));
//        });

        return http.build();
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
