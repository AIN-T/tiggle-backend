package com.gamja.tiggle.program.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.example.tiggle.common.annotation.PersistenceAdapter;
import org.example.tiggle.program.application.port.out.CreateProgramPort;
import org.example.tiggle.program.application.port.out.DeleteProgramPort;
import org.example.tiggle.program.domain.Program;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProgramPersistenceAdapter implements CreateProgramPort, DeleteProgramPort {
    private final JpaProgramRepository jpaProgramRepository;
    private final JpaProgramImageRepository jpaProgramImageRepository;

    @Override
    public void createProgram(Program program) {
        ProgramEntity entity = ProgramEntity.builder()
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
    public void deleteProgram(Program program) {
        ProgramEntity entity = ProgramEntity.builder()
                .id(program.getId())
                .build();
        jpaProgramRepository.delete(entity);
    }
}
