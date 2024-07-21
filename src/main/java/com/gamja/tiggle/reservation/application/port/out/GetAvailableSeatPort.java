package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.reservation.domain.Seat;

import java.util.List;

public interface GetAvailableSeatPort {
    List<Seat> getAvailableSeatByQuery(Long programId, Long timesId, Long sectionId);
    List<Seat> getAvailableSeatByJpa(Long programId, Long timesId, Long sectionId);
}
