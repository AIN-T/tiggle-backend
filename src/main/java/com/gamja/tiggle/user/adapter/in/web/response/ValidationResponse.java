package com.gamja.tiggle.user.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ValidationResponse {
    private Boolean isLogin;
}
