package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.application.out.GetSectionPort;
import com.gamja.tiggle.reservation.domain.Section;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class GetSectionAdapter implements GetSectionPort {

    private final SectionRepository sectionRepository;

    @Override
    public List<Section> getSection(Long id) throws BaseException {
        System.out.println("aa");
        List<SectionEntity> allByLocationId = sectionRepository.findAllByLocation_Id(id);
        if (allByLocationId.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_SECTION);
        }
        System.out.println(allByLocationId);
        System.out.println(allByLocationId.get(0).getId());
        return from(allByLocationId);
    }

    private List<Section> from(List<SectionEntity> allByLocationId) {
        return allByLocationId.stream().map(sectionEntity ->
                Section.builder()
                        .id(sectionEntity.getId())
                        .locationId(sectionEntity.getLocation().getId())
                        .gradeId(sectionEntity.getGrade().getId())
                        .sectionName(sectionEntity.getSectionName())
                        .build()
        ).toList();
    }
}
