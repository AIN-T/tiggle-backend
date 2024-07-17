package com.gamja.tiggle.section.application.port.in;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.section.domain.Section;

import java.util.List;

public interface GetSectionUseCase {

    List<Section> getSection(Long locationId) throws BaseException;
}
