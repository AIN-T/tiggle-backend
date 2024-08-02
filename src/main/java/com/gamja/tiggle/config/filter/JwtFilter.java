package com.gamja.tiggle.config.filter;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import com.gamja.tiggle.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String getCookieValue(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.toString();
                }
            }
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user = null;
        String authorization = null;
        String token = null;

        //authorization = request.getHeader("Authorization");
        authorization = getCookieValue(request, "AToken");


        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("Bearer 토큰이 없음");
            filterChain.doFilter(request, response);
            return;

        }
        try {
            token = authorization.split(" ")[1];
        } catch (NullPointerException e) {
            CustomAuthenticationEntryPoint authenticationEntryPoint = new CustomAuthenticationEntryPoint();
            BaseResponse br = new BaseResponse(BaseResponseStatus.NOT_INSERT_TOKEN);
            authenticationEntryPoint.commenceBaseResponse(request, response, br);
            return;
        }

        try {
            if (jwtUtil.isExpired(token)) {
                System.out.println("토큰 만료됨");
            }
        } catch (ExpiredJwtException e) {
            CustomAuthenticationEntryPoint authenticationEntryPoint = new CustomAuthenticationEntryPoint();
            BaseResponse br = new BaseResponse(BaseResponseStatus.EXPIRED_TOKEN);
            authenticationEntryPoint.commenceBaseResponse(request, response, br);
            return;
        } catch (MalformedJwtException eM) {
            CustomAuthenticationEntryPoint authenticationEntryPoint = new CustomAuthenticationEntryPoint();
            BaseResponse br = new BaseResponse(BaseResponseStatus.UNSIGNED_FORMAT_TOKEN);
            authenticationEntryPoint.commenceBaseResponse(request, response, br);
            return;
        }

        Long idx = null;
        String username = null;
        String role = null;
        try {
            idx = jwtUtil.getId(token);
            username = jwtUtil.getUsername(token);
            role = jwtUtil.getRole(token);
        } catch (NullPointerException e) {
            CustomAuthenticationEntryPoint authenticationEntryPoint = new CustomAuthenticationEntryPoint();
            BaseResponse br = new BaseResponse(BaseResponseStatus.NOT_MATCH_USERDATA_TOKEN);
            authenticationEntryPoint.commenceBaseResponse(request, response, br);
            return;
        }

        user = User.builder()
                .id(idx)
                .email(username)
                .role(role)
                .build();


        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().

                setAuthentication(authToken);


        filterChain.doFilter(request, response);


    }
}

