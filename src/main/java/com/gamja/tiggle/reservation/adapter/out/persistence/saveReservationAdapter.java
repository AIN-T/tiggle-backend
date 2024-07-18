package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.program.adapter.out.persistence.ProgramEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.TimesEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.application.port.out.SaveReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class saveReservationAdapter implements SaveReservationPort {

    private final ReservationRepository reservationRepository;

    @Override
    public void save(Reservation reservation) {

        ReservationEntity reservationEntity = from(reservation);
        reservationRepository.save(reservationEntity);
    }

    private static ReservationEntity from(Reservation reservation) {
        return ReservationEntity.builder()
                .program(new ProgramEntity(reservation.getProgramId()))
                .times(new TimesEntity(reservation.getTimesId()))
                .seat(new SeatEntity(reservation.getSeatId()))
                .status(ReservationType.IN_PROGRESS)
                .build();
    }
}
