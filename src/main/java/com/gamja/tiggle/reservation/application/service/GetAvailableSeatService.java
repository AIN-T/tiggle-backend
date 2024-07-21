package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.application.port.out.ProgramPort;
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
    private final ProgramPort programPort;


    @Override
    public List<Seat> getAvailableSeat(GetAvailableSeatCommand command) throws BaseException {

        System.out.println(command.getProgramId());
        if (!programPort.existProgram(command.getProgramId())) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_PROGRAM);
        }

        System.out.println("???");

        return getAvailableSeatPort.getAvailableSeatByQuery(
                command.getProgramId(),
                command.getTimesId(),
                command.getSectionId());
    }
}
