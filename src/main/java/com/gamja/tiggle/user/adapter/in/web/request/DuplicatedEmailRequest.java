package com.gamja.tiggle.user.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuplicatedEmailRequest {
    private String email;
}
