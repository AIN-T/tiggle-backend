package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAllSeatResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAvailableSeatResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.ReservationRepository;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.SeatRepository;
import com.gamja.tiggle.reservation.application.port.out.GetSeatPort;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class getSeatAdapter implements GetSeatPort {

    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    /**
     * N+1 성능 문제 해결
     * getAvailableSeatByJpa -> getAvailableSeatByQuery
     */
    @Override
    public List<Seat> getAvailableSeatByJpa(Long programId, Long timesId, Long sectionId) throws BaseException {
        // sectionId로 먼저 좌석 조회
        List<SeatEntity> seats = seatRepository.findAllBySectionEntityId(sectionId).orElseThrow(() ->
                new BaseException(BaseResponseStatus.NOT_FOUND_SEAT));


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
        List<GetAvailableSeatResponse> availableSeat = seatRepository.findAvailableSeat(programId, timesId, sectionId);

        List<Seat> list = availableSeat.stream().map(getAvailableSeatResponse ->
                Seat.builder()
                        .id(getAvailableSeatResponse.getSeatId())
                        .seatNumber(getAvailableSeatResponse.getSeatNumber())
                        .row(getAvailableSeatResponse.getRow())
                        .build()).toList();
        return list;
    }


    /**
     * 전체 좌석 조회 (예약 가능 여부까지는 X, 단순 좌석 배치도만 보내줌)
     * @param sectionId
     * @return
     * @throws BaseException
     */
    @Override
    public List<Seat> getAllSeat(Long sectionId) throws BaseException {

        List<SeatEntity> seats = seatRepository.findAllBySectionEntityIdByQuery(sectionId);
        if (seats.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_SEAT);
        }

        return seats.stream().map(seatEntity ->
                Seat.builder()
                        .id(seatEntity.getId())
                        .row(seatEntity.getRow())
                        .seatNumber(seatEntity.getSeatNumber())
                        .active(seatEntity.getActive())
                        .build()
        ).toList();
    }

    /**
     * 예약 여부까지  포함해서 전체 좌석을 보내줌
     * @param programId
     * @param sectionId
     * @param timesId
     * @return
     * @throws BaseException
     */
    @Override
    public List<Seat> getAllSeatWithEnable(Long programId, Long sectionId, Long timesId) throws BaseException {

        List<GetAllSeatResponse> allSeat = seatRepository.findAllSeat(programId, sectionId, timesId);
        for (int i = 0; i < allSeat.size(); i++) {
        System.out.println(allSeat.get(i).getActive());
        System.out.println(allSeat.get(i).getEnable());
            System.out.println("--");
        }

        return allSeat.stream().map(response ->
                Seat
                        .builder()
                        .id(response.getSeatId())
                        .enable(response.getEnable())
                        .seatNumber(response.getSeatNumber())
                        .row(response.getRow())
                        .active(response.getActive())
                        .enable(response.getEnable())
                        .build()
        ).toList();
    }
}

