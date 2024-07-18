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

    private String region_1depth_name;
    private String region_2depth_name;
    private String region_3depth_name;
    private String region_4depth_name;
    private String phoneNumber;
    private Date createdAt;
    private Date verifiedAt;
}

