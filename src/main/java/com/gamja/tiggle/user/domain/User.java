package com.gamja.tiggle.user.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String loginType;
    private Boolean status;
    private Boolean enable;
    private String role;
    private String address;
    private String phoneNumber;
    private Integer point;
    private Date createdAt;
    private Date verifiedAt;
}

