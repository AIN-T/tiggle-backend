package com.gamja.tiggle.user.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface EmailVerifyPort {
    String sendEmail(User user) throws BaseException;

    void saveVerify(String email, String uuid);
}

