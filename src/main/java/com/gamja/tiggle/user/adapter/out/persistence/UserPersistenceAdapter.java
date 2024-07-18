package com.gamja.tiggle.user.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.user.application.port.out.EmailVerifyPort;
import com.gamja.tiggle.user.application.port.out.UserPersistencePort;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    private final JpaUserRepository jpaUserRepository;
    private final EmailVerifyRepository emailVerifyRepository;

    @Override
    public void verifyUser(String email) throws BaseException {
        UserEntity result = jpaUserRepository.findByEmail(email);
        if (result!=null){
            UserEntity entity = UserEntity.builder()
                    .id(result.getId())
                    .email(result.getEmail())
                    .name(result.getName())
                    .password(result.getPassword())
                    .loginType(result.getLoginType())
                    .role("ROLE_USER")
                    .enable(true)
                    .status(result.getStatus())
                    .region_1depth_name(result.getRegion_1depth_name())
                    .region_2depth_name(result.getRegion_2depth_name())
                    .region_3depth_name(result.getRegion_3depth_name())
                    .region_4depth_name(result.getRegion_4depth_name())
                    .createdAt(result.getCreatedAt())
                    .build();

            entity.verifiedAt();

            jpaUserRepository.save(entity);
        }


    }

    @Override
    public void saveUser(User user) throws BaseException {
        UserEntity entity = UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .loginType(user.getLoginType())
                .status(user.getStatus())
                .enable(user.getEnable())
                .region_1depth_name(user.getRegion_1depth_name())
                .region_2depth_name(user.getRegion_2depth_name())
                .region_3depth_name(user.getRegion_3depth_name())
                .region_4depth_name(user.getRegion_4depth_name())
                .phoneNumber(user.getPhoneNumber())
                .build();

        entity.createdAt();

        jpaUserRepository.save(entity);
    }
}

