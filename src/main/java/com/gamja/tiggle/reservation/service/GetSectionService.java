package com.gamja.tiggle.reservation.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.reservation.application.in.GetSectionUseCase;
import com.gamja.tiggle.reservation.application.out.GetSectionPort;
import com.gamja.tiggle.reservation.domain.Section;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetSectionService implements GetSectionUseCase {

    private final GetSectionPort getSectionPort;

    @Override
    public List<Section> getSection(Long locationId) throws BaseException {
        System.out.println("locationId = " + locationId);
        return getSectionPort.getSection(locationId);
    }
}
