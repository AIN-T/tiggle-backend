package com.gamja.tiggle.program.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.example.tiggle.common.BaseException;
import org.example.tiggle.common.BaseResponse;
import org.example.tiggle.common.BaseResponseStatus;
import org.example.tiggle.common.annotation.WebAdapter;
import org.example.tiggle.program.adapter.in.web.request.CreateProgramRequest;
import org.example.tiggle.program.application.port.in.CreateProgramCommand;
import org.example.tiggle.program.application.port.in.CreateProgramUseCase;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/program")
public class CreateProgramController {
    private final CreateProgramUseCase createUseCase;

    @PostMapping("/create")
    public BaseResponse<String> create(@RequestPart CreateProgramRequest request, @RequestPart MultipartFile[] files) throws BaseException {
        CreateProgramCommand command = CreateProgramCommand.builder()
                .programName(request.getProgramName())
                .programInfo(request.getProgramInfo())
                .reservationOpenDate(request.getReservationOpenDate())
                .reservationCloseDate(request.getReservationCloseDate())
                .age(request.getAge())
                .runtime(request.getRuntime())
                .salerInfo(request.getSalerInfo())
                .programStartDate(request.getProgramStartDate())
                .programEndDate(request.getProgramEndDate())
                .imageFiles(files)
                .build();

        createUseCase.createProgram(command);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

}
