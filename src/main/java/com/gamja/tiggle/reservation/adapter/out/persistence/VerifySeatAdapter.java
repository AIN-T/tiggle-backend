package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.application.port.out.VerifySeatPort;
import com.gamja.tiggle.reservation.domain.Seat;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class VerifySeatAdapter implements VerifySeatPort {

    private final ReservationRepository reservationRepository;


    @Override
    public void verifySeat(Seat seat) throws BaseException {

        Optional<ReservationEntity> verifiedSeat = reservationRepository.findBySeatEntityId(seat.getId());
        if (verifiedSeat.isPresent() && verifiedSeat.get().getStatus() != ReservationType.AVAILABLE) {
            throw new BaseException(BaseResponseStatus.ALREADY_CHOSEN_SEAT);
        }

    }
}
