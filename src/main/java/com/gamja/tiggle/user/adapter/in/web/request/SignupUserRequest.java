package com.gamja.tiggle.user.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupUserRequest {
    private String name;
    private String email;
    private String password;
    private String loginType;
    private String address;
    private String phoneNumber;
}
