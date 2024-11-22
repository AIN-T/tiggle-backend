package com.gamja.tiggle.reservation.application.port.in;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Section;

import java.util.List;

public interface GetSectionUseCase {

    List<Section> getSection(Long locationId, Long programId, Long timesId) throws BaseException;
}
