package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.application.port.out.ProgramPort;
import com.gamja.tiggle.reservation.application.port.in.GetSectionUseCase;
import com.gamja.tiggle.reservation.application.port.out.GetSectionPort;
import com.gamja.tiggle.reservation.domain.Section;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetSectionService implements GetSectionUseCase {

    private final GetSectionPort getSectionPort;
    private final ProgramPort programPort;

    @Override
    public List<Section> getSection(Long locationId, Long programId) throws BaseException {
        programPort.findByProgramIdAndLocationId(programId,locationId);
        return getSectionPort.getSection(locationId, programId);
    }
}
