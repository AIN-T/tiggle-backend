package com.gamja.tiggle.section.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.section.adapter.out.persistence.SectionEntity;
import com.gamja.tiggle.section.domain.Section;

import java.util.List;

public interface GetSectionPort {

    List<Section> getSection(Long id) throws BaseException;
}
