package com.gamja.tiggle.user.application.service;


import com.gamja.tiggle.user.adapter.out.persistence.JpaUserRepository;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity result = jpaUserRepository.findByEmail(username);
        User user = User.builder()
                .id(result.getId())
                .name(result.getEmail())
                .password(result.getPassword())
                .role(result.getRole())
                .point(result.getPoint())
                .enable(true)
                .build();

        if (result != null) {
            return new CustomUserDetails(user);
        } else {
            return null;
        }

    }
}
