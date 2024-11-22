package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Seat;

import java.util.List;

public interface GetSeatPort {
    List<Seat> getAvailableSeatByQuery(Long programId, Long timesId, Long sectionId);

    List<Seat> getAllSeat(Long sectionId) throws BaseException;

    List<Seat> getAllSeatWithEnable(Long programId, Long sectionId, Long timesId) throws BaseException;

    List<Seat> getAvailableExchang(Long programId, Long sectionId, Long timesId, Long userId);
}
