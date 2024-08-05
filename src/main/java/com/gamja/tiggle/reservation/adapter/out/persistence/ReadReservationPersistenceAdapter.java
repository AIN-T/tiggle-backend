package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import lombok.RequiredArgsConstructor;


@PersistenceAdapter
@RequiredArgsConstructor
public class ReadReservationPersistenceAdapter implements ReadReservationPort {

    private final ReservationRepository repository;

    @Override
    public ReservationEntity read(Long reservationId) throws BaseException {
        return repository.findById(reservationId).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_RESERVATION));
    }

    @Override
    public Reservation readReservation(Reservation reservation) throws BaseException {
        ReservationEntity result = repository.findReservationWithDetails(reservation.getId());
        Reservation reservations = Reservation.builder()
                .ticketNumber(result.getTicketNumber())
                .createdAt(result.getCreatedAt())
                .date(result.getTimesEntity().getDate())
                .locationName(result.getProgramEntity().getLocationEntity().getLocationName())
                .name(result.getUser().getName())
                .seatInfo(
                        result.getSeatEntity().getRow()+"구역 " +result.getSeatEntity().getSectionEntity().getColumnCount()+"열 "+result.getSeatEntity().getSeatNumber()+"번")
                .totalPrice(result.getTotalPrice())
                .gradeName(result.getSeatEntity().getSectionEntity().getGradeEntity().getGradeName())
                .status(result.getStatus())
                .build();
        return reservations;
    }
}
