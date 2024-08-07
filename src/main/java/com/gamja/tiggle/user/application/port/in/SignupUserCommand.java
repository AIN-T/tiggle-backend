package com.gamja.tiggle.user.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupUserCommand {
    private String name;
    private String email;
    private String password;
    private String loginType;
    private Boolean status;
    private Boolean enable;
    private String address;
    private String phoneNumber;

}
