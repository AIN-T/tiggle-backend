package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramGradeEntity;
import com.gamja.tiggle.program.application.port.out.GetPricePort;
import com.gamja.tiggle.program.domain.ProgramGrade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProgramGradeAdapter implements GetPricePort {

    private final ProgramGradeRepository programGradeRepository;

    @Override
    public List<ProgramGrade> getPrice(Long id) throws BaseException {

        // TODO N+1
        List<ProgramGradeEntity> allByProgramEntityId = programGradeRepository.findAllByProgramEntityId(id);
        if (allByProgramEntityId.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_PRICE);
        }
        return allByProgramEntityId.stream().map(programGradeEntity ->
                ProgramGrade.builder()
                        .id(programGradeEntity.getId())
                        .gradeId(programGradeEntity.getGradeEntity().getId())
                        .gradeName(programGradeEntity.getGradeEntity().getGradeName())
                        .price(programGradeEntity.getPrice())
                        .programId(programGradeEntity.getProgramEntity().getId())
                        .build()
        ).toList();
    }
}
