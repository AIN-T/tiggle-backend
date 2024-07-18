package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.reservation.application.port.in.GetRemainedSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetRemainedSeatUseCase;
import com.gamja.tiggle.reservation.application.port.out.GetRemainedSeatPort;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetRemainedSeatService implements GetRemainedSeatUseCase {
    private final GetRemainedSeatPort getRemainedSeatPort;

    @Override
    public List<Seat> getRemainedSeat(GetRemainedSeatCommand command) {
        return getRemainedSeatPort.getRemainedSeat(command.getProgramId(),
                command.getTimesId(),
                command.getSectionId());

    }
}
