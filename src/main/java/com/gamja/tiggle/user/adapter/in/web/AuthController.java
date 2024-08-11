package com.gamja.tiggle.user.adapter.in.web;

import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.user.adapter.in.web.response.ValidationResponse;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("")
    @Operation(summary = "유효성 검증", description = "로그인 한 사용자인지 검증하는 API 입니다.")
    public BaseResponse<ValidationResponse> myPage(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Boolean isLogin = null;

        if (customUserDetails == null) {
            isLogin = false;
        } else {
            isLogin = true;
        }

        return new BaseResponse<>(ValidationResponse.builder().isLogin(isLogin).build());
    }
}
