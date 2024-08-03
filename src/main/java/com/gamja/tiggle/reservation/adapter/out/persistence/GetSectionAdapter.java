package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SectionEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.SectionRepository;
import com.gamja.tiggle.reservation.application.port.out.GetSectionPort;
import com.gamja.tiggle.reservation.domain.Section;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class GetSectionAdapter implements GetSectionPort {

    private final SectionRepository sectionRepository;

    @Override
    public List<Section> getSection(Long id) throws BaseException {
        List<SectionEntity> allByLocationId = sectionRepository.findAllByLocationEntityId(id);
        if (allByLocationId.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_SECTION);
        }
        return from(allByLocationId);
    }

    @Override
    public Section getRowColumn(Long id) throws BaseException {
        SectionEntity sectionEntity = sectionRepository.findById(id).orElseThrow(() ->
                new BaseException(BaseResponseStatus.NOT_FOUND_SECTION));
        return from(sectionEntity);
    }

    private List<Section> from(List<SectionEntity> allByLocationId) {
        return allByLocationId.stream().map(sectionEntity ->
                Section.builder()
                        .id(sectionEntity.getId())
                        .locationId(sectionEntity.getLocationEntity().getId())
                        .gradeId(sectionEntity.getGradeEntity().getId())
                        .sectionName(sectionEntity.getSectionName())
                        .build()
        ).toList();
    }

    private Section from(SectionEntity entity) {
        return Section
                .builder()
                .rowCount(entity.getRowCount())
                .columnCount(entity.getColumnCount())
                .build();
    }
}
