package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.application.port.out.ProgramPort;
import com.gamja.tiggle.reservation.application.port.in.GetAllSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetSeatUseCase;
import com.gamja.tiggle.reservation.application.port.out.GetSeatPort;
import com.gamja.tiggle.reservation.application.port.out.GetSectionPort;
import com.gamja.tiggle.reservation.application.port.out.GetTimesPort;
import com.gamja.tiggle.reservation.domain.Seat;
import com.gamja.tiggle.reservation.domain.Section;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetSeatService implements GetSeatUseCase {
    private final GetSeatPort getSeatPort;
    private final ProgramPort programPort;
    private final GetSectionPort sectionPort;
    private final GetTimesPort timesPort;


    @Override
    public List<Seat> getAvailableSeat(GetAvailableSeatCommand command) throws BaseException {

        Long locationId = programPort.getLocationId(command.getProgramId());
        programPort.existProgram(command.getProgramId());
        timesPort.verifyTimes(command.getTimesId(), command.getProgramId());
        sectionPort.correctSection(command.getSectionId(), locationId);


        return getSeatPort.getAvailableSeatByQuery(
                command.getProgramId(),
                command.getTimesId(),
                command.getSectionId());
    }

    @Override
    public List<List<Seat>> getAllSeat(GetAllSeatCommand command) throws BaseException {

        List<Seat> allSeat = getSeatPort.getAllSeat(command.getProgramId(),
                command.getSectionId(),
                command.getTimesId()
        );
        Long locationId = programPort.getLocationId(command.getProgramId());
        programPort.existProgram(command.getProgramId());
        timesPort.verifyTimes(command.getTimesId(), command.getProgramId());
        sectionPort.correctSection(command.getSectionId(), locationId);

        Section section = sectionPort.getRowColumn(command.getSectionId());
        int rowCount = section.getRowCount();
        int columnCount = section.getColumnCount();
        List<List<Seat>> ArraySeat = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            ArraySeat.add(new ArrayList<>(Collections.nCopies(columnCount, null)));
        }

        for (Seat seat : allSeat) {
            int rowIndex = seat.getRow().charAt(0) - 'A';
            int colIndex = seat.getSeatNumber() - 1;

            if (colIndex >= columnCount) throw new BaseException(BaseResponseStatus.NOT_MATCH_ROW);

            if (rowIndex >= rowCount) throw new BaseException(BaseResponseStatus.NOT_MATCH_COLUMN);

            ArraySeat.get(rowIndex).set(colIndex, seat);
        }


        return ArraySeat;
    }


}
