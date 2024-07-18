package com.gamja.tiggle.program.adapter.in.web;

import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.program.adapter.in.web.request.CreateProgramRequest;
import com.gamja.tiggle.program.application.port.in.CreateProgramCommand;
import com.gamja.tiggle.program.application.port.in.CreateProgramUseCase;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/program")
public class CreateProgramController {
    private final CreateProgramUseCase createUseCase;

    @PostMapping("/create")
    public BaseResponse<String> create(@RequestPart CreateProgramRequest request, @RequestPart MultipartFile[] files) {
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
                .categoryId(request.getCategoryId())
                .build();
        try {
            createUseCase.createProgram(command);

        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        return new BaseResponse(BaseResponseStatus.SUCCESS);
    }
}
