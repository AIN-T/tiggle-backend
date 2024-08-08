package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Section;

import java.util.List;

public interface GetSectionPort {

    List<Section> getSection(Long locationId, Long programId) throws BaseException;
    Section getRowColumn(Long id) throws BaseException;
    void correctSection(Long sectionId, Long locationId) throws BaseException;
}
