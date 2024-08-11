package com.gamja.tiggle.user.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.user.application.port.out.UserPersistencePort;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
                    .role("ROLE_MEMBER")
                    .enable(true)
                    .status(result.getStatus())
                    .address(result.getAddress())
                    .createdAt(result.getCreatedAt())
                    .point(result.getPoint())
                    .build();

            entity.verifiedAt();

            jpaUserRepository.save(entity);
        }


    }

    @Override
    public void saveUser(User user) throws BaseException {
        UserEntity entity = UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .loginType(user.getLoginType())
                .status(user.getStatus())
                .enable(user.getEnable())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .build();

        entity.createdAt();

        jpaUserRepository.save(entity);
    }

    @Override
    public User searchUser(Long id) throws BaseException {
        Optional<UserEntity> userEntity = jpaUserRepository.findById(id);
        if (userEntity.isPresent()) {
            return User.builder()
                    .id(userEntity.get().getId())
                    .name(userEntity.get().getName())
                    .email(userEntity.get().getEmail())
                    .password(userEntity.get().getPassword())
                    .loginType(userEntity.get().getLoginType())
                    .status(userEntity.get().getStatus())
                    .enable(userEntity.get().getEnable())
                    .address(userEntity.get().getAddress())
                    .phoneNumber(userEntity.get().getPhoneNumber())
                    .point(userEntity.get().getPoint())
                    .build();
        }
        else {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
    }

    @Override
    public void savePoint(Long id, Integer point) throws BaseException {
        Optional<UserEntity> userEntity = jpaUserRepository.findById(id);
        userEntity.get().setPoint(userEntity.get().getPoint() + point);
        jpaUserRepository.save(userEntity.get());
    }

    @Override
    public void existUser(Long id) throws BaseException {
        boolean exists = jpaUserRepository.existsById(id);
        if (!exists){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
    }

    @Override
    public Boolean existEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }
}

