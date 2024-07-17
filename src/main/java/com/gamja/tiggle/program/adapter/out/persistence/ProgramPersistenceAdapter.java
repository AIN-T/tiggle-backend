package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.category.adapter.out.persistence.CategoryEntity;
import com.gamja.tiggle.category.adapter.out.persistence.JpaCategoryRepository;
import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.program.application.port.out.ProgramPort;
import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.program.application.port.out.CreateProgramPort;
import com.gamja.tiggle.program.domain.Program;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProgramPersistenceAdapter implements CreateProgramPort, ProgramPort {
    private final JpaProgramRepository jpaProgramRepository;
    private final JpaProgramImageRepository jpaProgramImageRepository;
    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public void createProgram(Program program) throws BaseException {

        CategoryEntity category = jpaCategoryRepository.findById(program.getCategoryIdx())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FAIL));

        ProgramEntity entity = ProgramEntity.builder()
                .categoryEntity(category)
                .programName(program.getProgramName())
                .programInfo(program.getProgramInfo())
                .programStartDate(program.getProgramStartDate())
                .programEndDate(program.getProgramEndDate())
                .build();
        jpaProgramRepository.save(entity);


        for (String uploadFilePath : program.getImageUrls()) {
            ProgramImageEntity programImageEntity = ProgramImageEntity.builder()
                    .imgUrl(uploadFilePath)
                    .programEntity(entity)
                    .build();
            jpaProgramImageRepository.save(programImageEntity);
        }

    }

    @Override
    public boolean existProgram(Long id) {
        return jpaProgramRepository.existsById(id);
    }
}
