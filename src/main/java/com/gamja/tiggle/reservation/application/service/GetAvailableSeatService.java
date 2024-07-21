package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatUseCase;
import com.gamja.tiggle.reservation.application.port.out.GetAvailableSeatPort;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAvailableSeatService implements GetAvailableSeatUseCase {
    private final GetAvailableSeatPort getAvailableSeatPort;

    @Override
    public List<Seat> getAvailableSeat(GetAvailableSeatCommand command) {
        return getAvailableSeatPort.getAvailableSeatByQuery(
                command.getProgramId(),
                command.getTimesId(),
                command.getSectionId());
    }
}
