package com.gamja.tiggle.user.adapter.out.verify;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.user.adapter.out.persistence.EmailVerify;
import com.gamja.tiggle.user.adapter.out.persistence.EmailVerifyRepository;
import com.gamja.tiggle.user.adapter.out.persistence.JpaUserRepository;
import com.gamja.tiggle.user.application.port.out.EmailVerifyPort;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmailVerifyAdapter implements EmailVerifyPort {
    private final JavaMailSender emailSender;
    private final EmailVerifyRepository emailVerifyRepository;

    @Override
    public String sendEmail(User user) throws BaseException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("[내 사이트] 가입 환영");
        String uuid = UUID.randomUUID().toString();
        message.setText("http://localhost:8080/user/verify?email="+user.getEmail()+"&uuid="+uuid);
        emailSender.send(message);

        return uuid;
    }

    @Override
    public void saveVerify(String email, String uuid) {
        EmailVerify emailVerify = EmailVerify.builder()
                .email(email)
                .uuid(uuid)
                .build();

        emailVerifyRepository.save(emailVerify);
    }

    @Override
    public EmailVerify findByEmail(String email) throws BaseException {
        EmailVerify result = emailVerifyRepository.findByEmail(email);
        if (result != null){
            return result;
        }
        else {
            throw new BaseException(BaseResponseStatus.BAD_REQUEST);
        }

    }
}

