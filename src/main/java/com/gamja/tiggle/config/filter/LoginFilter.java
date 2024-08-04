package com.gamja.tiggle.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamja.tiggle.user.adapter.in.web.request.LoginUserRequest;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public LoginFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthenticationManager authenticationManager1) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager1;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        LoginUserRequest loginUserRequest;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            loginUserRequest = objectMapper.readValue(messageBody, LoginUserRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String username = loginUserRequest.getEmail();
        String password = loginUserRequest.getPassword();

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username,
                        password, null);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        GrantedAuthority auth = authorities.iterator().next();
        String role = auth.getAuthority();
        String username = user.getUsername();
        Long id = user.getUser().getId();

        String token = jwtUtil.createToken(id, username, role);

        Cookie loginCookie = new Cookie("AToken", token);
        loginCookie.setHttpOnly(true);
        loginCookie.setSecure(true);
        loginCookie.setPath("/");
        loginCookie.setMaxAge(60*60*1);

        response.addCookie(loginCookie);
    }
}
