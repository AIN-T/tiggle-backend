package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.SectionEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.Response.GetSectionResponse;
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
    public List<Section> getSection(Long locationId, Long programId) throws BaseException {
        List<GetSectionResponse> allByLocationId = sectionRepository.findAllByLocationEntityIdWithRemainingCount(locationId, programId);
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

    @Override
    public void correctSection(Long sectionId, Long locationId) throws BaseException {
        if(!sectionRepository.existsByIdAndLocationEntityId(sectionId, locationId)){
            throw new BaseException(BaseResponseStatus.NOT_MATCH_SECTION);
        }
    }

    private List<Section> from(List<GetSectionResponse> allByLocationId) {
        return allByLocationId.stream().map(sectionEntity ->
                Section.builder()
                        .id(sectionEntity.getSectionId())
                        .locationId(sectionEntity.getSectionId())
                        .gradeId(sectionEntity.getGradeId())
                        .gradeName(sectionEntity.getGradeName())
                        .sectionName(sectionEntity.getSectionName())
                        .price(sectionEntity.getPrice())
                        .remainingCount(sectionEntity.getRemainingCount())
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
