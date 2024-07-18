package com.gamja.tiggle.user.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyUserCommand {
    private String email;
    private String uuid;


}
