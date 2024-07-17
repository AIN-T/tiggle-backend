package com.gamja.tiggle.program.application.service;

import lombok.RequiredArgsConstructor;
import org.example.tiggle.common.annotation.UseCase;
import org.example.tiggle.program.application.port.in.CreateProgramCommand;
import org.example.tiggle.program.application.port.in.CreateProgramUseCase;
import org.example.tiggle.program.application.port.in.DeleteProgramCommand;
import org.example.tiggle.program.application.port.in.DeleteProgramUseCase;
import org.example.tiggle.program.application.port.out.CreateProgramPort;
import org.example.tiggle.program.application.port.out.DeleteProgramPort;
import org.example.tiggle.program.application.port.out.S3UploadPort;
import org.example.tiggle.program.domain.Program;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateProgramService implements CreateProgramUseCase,DeleteProgramUseCase {
    private final CreateProgramPort createPersistencePort;
    private final DeleteProgramPort deletePersistencePort;
    private final S3UploadPort s3UploadPort;


    @Override
    public void createProgram(CreateProgramCommand command){
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
                .build();
        createPersistencePort.createProgram(program);
    }

    @Override
    public void deleteProgram(DeleteProgramCommand command) {
        Program program = Program.builder()
                .id(command.getId())
                .build();
        deletePersistencePort.deleteProgram(program);
    }

}
