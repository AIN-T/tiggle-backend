package com.gamja.tiggle.user.adapter.out.persistence;

import com.gamja.tiggle.user.application.port.out.UserPersistencePort;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public void saveUser(User user) {
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

