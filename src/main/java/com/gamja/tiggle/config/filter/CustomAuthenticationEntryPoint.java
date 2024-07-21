package com.gamja.tiggle.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamja.tiggle.common.BaseResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    }

    public void commenceBaseResponse(HttpServletRequest request, HttpServletResponse response, BaseResponse br) throws IOException, ServletException {
        setResponse(response, br);
        Map<String, Object> res = new HashMap<>();
        res.put("FILTER ERROR", br);
        String json = new ObjectMapper().writeValueAsString(res);
        response.getWriter().write(json);
    }

    private void setResponse(HttpServletResponse response, BaseResponse exceptionDto) {
        response.setStatus(exceptionDto.getCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
}