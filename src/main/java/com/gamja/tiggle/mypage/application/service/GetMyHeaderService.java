package com.gamja.tiggle.mypage.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.exchange.application.port.out.ExchangePort;
import com.gamja.tiggle.mypage.application.port.in.GetMyHeaderUseCase;
import com.gamja.tiggle.mypage.domain.MyHeaderInfo;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.user.application.port.out.UserPersistencePort;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;

@Component
@RequiredArgsConstructor
public class GetMyHeaderService implements GetMyHeaderUseCase {
    private final UserPersistencePort userPersistencePort;
    private final ReadReservationPort readReservationPort;
    private final ExchangePort exchangePort;

    @Override
    public MyHeaderInfo getByUserId(Long id) throws BaseException {
        User user = userPersistencePort.searchUser(id);
        return MyHeaderInfo.builder()
                .username(user.getName())
                .reservationCnt((readReservationPort.getReservationCnt(id)).intValue())
                .notWatchedYet((exchangePort.readExchangeOfferCnt(id)).intValue())
                .point(user.getPoint())
                .build();
    }
}

