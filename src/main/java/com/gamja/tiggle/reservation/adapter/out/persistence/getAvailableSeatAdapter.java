package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetSeatResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.SeatRepository;
import com.gamja.tiggle.reservation.application.port.out.GetAvailableSeatPort;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class getAvailableSeatAdapter implements GetAvailableSeatPort {

    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    /**
     * N+1 성능 문제 해결
     * getAvailableSeatByJpa -> getAvailableSeatByQuery
     */
    @Override
    public List<Seat> getAvailableSeatByJpa(Long programId, Long timesId, Long sectionId) {
        // sectionId로 먼저 좌석 조회
        List<SeatEntity> seats = seatRepository.findAllBySectionEntityId(sectionId);
        System.out.println("seats = " + seats);

        List<SeatEntity> result = new ArrayList<>();
        for (SeatEntity seat : seats) {
            // 좌석 ID, 프로그램 ID, 시간 ID로 예약 정보 조회
            List<ReservationEntity> reservationList = reservationRepository.findBySeatEntityIdAndProgramEntityIdAndTimesEntityId(seat.getId(), programId, timesId);

            // 예약 정보가 없는 경우, 해당 좌석을 결과 목록에 추가
            if (reservationList.isEmpty()) {
                result.add(seat);
                continue;
            }

            ReservationEntity latestReservation = reservationList.get(0);
            for (ReservationEntity reservation : reservationList) {
                if ((reservation.getModifiedAt() != null && (latestReservation.getModifiedAt() == null || reservation.getModifiedAt().isAfter(latestReservation.getModifiedAt()))) ||
                        (reservation.getModifiedAt() == null && reservation.getCreatedAt().isAfter(latestReservation.getCreatedAt()))) {
                    latestReservation = reservation;
                }
            }

            if (latestReservation.isAvailable()) {
                result.add(seat);
            }
        }

        List<Seat> list = result.stream().map(seatEntity ->
                Seat.builder()
                        .id(seatEntity.getId())
                        .seatNumber(seatEntity.getSeatNumber())
                        .sectionId(seatEntity.getSectionEntity().getId())
                        .build()
        ).collect(Collectors.toList());

        return list;
    }

    @Override
    public List<Seat> getAvailableSeatByQuery(Long programId, Long timesId, Long sectionId) {
        List<GetSeatResponse> availableSeat = seatRepository.findAvailableSeat(programId, timesId, sectionId);

        List<Seat> list = availableSeat.stream().map(getSeatResponse ->
                Seat.builder()
                        .id(getSeatResponse.getSeatId())
                        .seatNumber(getSeatResponse.getSeatNumber())
                        .sectionId(getSeatResponse.getSectionId())
                        .build()).toList();
        return list;
    }
}

