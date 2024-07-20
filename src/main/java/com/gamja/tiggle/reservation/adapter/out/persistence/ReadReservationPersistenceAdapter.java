package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;


@PersistenceAdapter
@RequiredArgsConstructor
public class ReadReservationPersistenceAdapter implements ReadReservationPort {

    private final ReservationRepository repository;

    @Override
    public ReservationEntity read(Reservation reservation) throws BaseException {
        return repository.findById(reservation.getId()).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_RESERVATION));
    }

    @Override
    public List<ReservationEntity> readExchangeOfferForMe(User user) {
        return repository.findReservationByUser(user.getId());
    }
}
