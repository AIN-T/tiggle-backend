package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.user.domain.User;

import java.util.List;

public interface ReadReservationPort {
    ReservationEntity read(Long reservationId) throws BaseException;
    List<ReservationEntity> readExchangeOfferForMe(User user);
}
