package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SeatEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAllSeatPersistentResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAvailableExchangeSeatPersistenceResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetAvailableSeatResponse;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.SeatRepository;
import com.gamja.tiggle.reservation.application.port.out.GetSeatPort;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class GetSeatAdapter implements GetSeatPort {

    private final SeatRepository seatRepository;
    private final RedisTemplate redisTemplate;


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
     *
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
     *
     * @param programId
     * @param sectionId
     * @param timesId
     * @return
     * @throws BaseException
     */
    @Override
    public List<Seat> getAllSeatWithEnable(Long programId, Long sectionId, Long timesId) throws BaseException {


        List<GetAllSeatPersistentResponse> allSeat
                = seatRepository.findAllSeat(programId, timesId, sectionId);

        return getSeatListWithRedisStatus(programId, timesId, sectionId, allSeat);
    }

    @Override
    public List<Seat> getAvailableExchang(Long programId, Long sectionId, Long timesId, Long userId) {
        List<GetAvailableExchangeSeatPersistenceResponse> allSeat
                = seatRepository.findAvailableExchange(programId, timesId, sectionId, userId);

        return getExchangeSeatList(allSeat);
    }

    @NotNull
    private List<Seat> getSeatListWithRedisStatus(Long programId, Long timesId, Long sectionId, List<GetAllSeatPersistentResponse> allSeat) {

        String redisKey = "seat:" + programId + ":" + timesId + ":" + sectionId;

        return allSeat.stream().map(response -> {
            boolean isLocked = redisTemplate.opsForSet().isMember(redisKey, response.getSeatId().toString());

            boolean enable = !isLocked && response.getEnable();

            return Seat.builder()
                    .id(response.getSeatId())
                    .enable(enable) // Redis 상태 반영
                    .seatNumber(response.getSeatNumber())
                    .row(response.getRow())
                    .active(response.getActive())
                    .build();
        }).toList();
    }


    @NotNull
    private static List<Seat> getExchangeSeatList(List<GetAvailableExchangeSeatPersistenceResponse> allSeat) {
        return allSeat.stream().map(response ->
                Seat
                        .builder()
                        .id(response.getSeatId())
                        .enable(response.getEnable())
                        .seatNumber(response.getSeatNumber())
                        .row(response.getRow())
                        .active(response.getActive())
                        .enable(response.getEnable())
                        .reservationId(response.getReservationId())
                        .pirce(response.getPrice())
                        .build()
        ).toList();
    }
}

