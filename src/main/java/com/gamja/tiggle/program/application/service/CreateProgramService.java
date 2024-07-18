package com.gamja.tiggle.program.application.service;

import com.gamja.tiggle.common.BaseException;
import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.application.port.in.CreateProgramCommand;
import com.gamja.tiggle.program.application.port.in.CreateProgramUseCase;
import com.gamja.tiggle.program.application.port.out.CreateProgramPort;
import com.gamja.tiggle.program.application.port.out.S3UploadPort;
import com.gamja.tiggle.program.domain.Program;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateProgramService implements CreateProgramUseCase {
    private final CreateProgramPort createPersistencePort;
    private final S3UploadPort s3UploadPort;


    @Override
    public void createProgram(CreateProgramCommand command) throws BaseException {
        List<String> uploadFilePaths = s3UploadPort.uploadProductImages(command.getImageFiles());

        Program program = Program.builder()
                .programName(command.getProgramName())
                .programInfo(command.getProgramInfo())
                .reservationOpenDate(command.getReservationOpenDate())
                .reservationCloseDate(command.getReservationCloseDate())
                .age(command.getAge())
                .runtime(command.getRuntime())
                .salerInfo(command.getSalerInfo())
                .programStartDate(command.getProgramStartDate())
                .programEndDate(command.getProgramEndDate())
                .imageUrls(uploadFilePaths)
                .categoryId(command.getCategoryId())
                .build();
        createPersistencePort.createProgram(program);
    }


}
