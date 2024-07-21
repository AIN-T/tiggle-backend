package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.TimesEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.application.port.out.SaveReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveReservationAdapter implements SaveReservationPort {

    private final ReservationRepository reservationRepository;

    @Override
    public void save(Reservation reservation) {

        ReservationEntity reservationEntity = from(reservation);
        reservationRepository.save(reservationEntity);
    }

    private static ReservationEntity from(Reservation reservation) {
        return ReservationEntity.builder()
                .programEntity(new ProgramEntity(reservation.getProgramId()))
                .timesEntity(new TimesEntity(reservation.getTimesId()))
                .seatEntity(new SeatEntity(reservation.getSeatId()))
                .ticketNumber(reservation.getTicketNumber())
                .requestLimit(reservation.getRequestLimit())
                .totalPrice(reservation.getTotalPrice())
                .user(new UserEntity(reservation.getUserId()))
                .ticketNumber(reservation.getTicketNumber())
                .status(ReservationType.IN_PROGRESS)
                .build();
    }

    @Override
    public void update(Reservation reservation) {

        ReservationEntity reservationEntity = updateFrom(reservation);
        reservationRepository.save(reservationEntity);
    }

    private static ReservationEntity updateFrom(Reservation reservation) {
        return ReservationEntity.builder()
                .id(reservation.getId())
                .user(new UserEntity(reservation.getId()))
                .programEntity(new ProgramEntity(reservation.getProgramId()))
                .timesEntity(new TimesEntity(reservation.getTimesId()))
                .seatEntity(new SeatEntity(reservation.getSeatId()))
                .ticketNumber(reservation.getTicketNumber())
                .totalPrice(reservation.getTotalPrice())
                .requestLimit(reservation.getRequestLimit())
                .status(reservation.getStatus())
                .build();
    }
}
