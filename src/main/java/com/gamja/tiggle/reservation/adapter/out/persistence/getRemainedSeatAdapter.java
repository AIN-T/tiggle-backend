package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.SeatRepository;
import com.gamja.tiggle.reservation.application.port.out.GetRemainedSeatPort;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class getRemainedSeatAdapter implements GetRemainedSeatPort {

    private final SeatRepository seatRepository;

    @Override
    public List<Seat> getRemainedSeat(Long programId, Long timesId, Long sectionId) {
        return getSeats(programId, timesId, sectionId);
    }

    private List<Seat> getSeats(Long programId, Long timesId, Long sectionId) {
        return seatRepository.findAvailableSeat(programId, timesId, sectionId).stream().map(getRemainedSeatResponse ->
                Seat.builder()
                        .id(getRemainedSeatResponse.getSeatId())
                        .seatNumber(getRemainedSeatResponse.getSeatNumber())
                        .sectionId(getRemainedSeatResponse.getSectionId())
                        .build()).toList();
    }
}
