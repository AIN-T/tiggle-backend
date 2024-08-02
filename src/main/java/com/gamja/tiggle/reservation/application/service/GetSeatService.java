package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.application.port.out.ProgramPort;
import com.gamja.tiggle.reservation.application.port.in.GetAllSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetSeatUseCase;
import com.gamja.tiggle.reservation.application.port.out.GetSeatPort;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetSeatService implements GetSeatUseCase {
    private final GetSeatPort getSeatPort;
    private final ProgramPort programPort;


    @Override
    public List<Seat> getAvailableSeat(GetAvailableSeatCommand command) throws BaseException {

        if (!programPort.existProgram(command.getProgramId())) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_PROGRAM);
        }
        return getSeatPort.getAvailableSeatByQuery(
                command.getProgramId(),
                command.getTimesId(),
                command.getSectionId());
    }

    @Override
    public List<Seat> getAllSeat(GetAllSeatCommand command) throws BaseException {

        return getSeatPort.getAllSeat(command.getProgramId(),
                command.getSectionId(),
                command.getTimesId(),
                command.getRowCount(),
                command.getColumnCount());
    }


}
