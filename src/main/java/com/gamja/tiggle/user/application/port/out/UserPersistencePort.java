package com.gamja.tiggle.user.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import com.gamja.tiggle.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserPersistencePort {
    void saveUser(User user) throws BaseException;

    void verifyUser(String email) throws BaseException;

    User searchUser(Long id) throws BaseException;

    void savePoint(Long id, Integer point) throws BaseException;
}

