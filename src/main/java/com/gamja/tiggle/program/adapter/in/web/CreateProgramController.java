package com.gamja.tiggle.program.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.program.adapter.in.web.request.CreateProgramRequest;
import com.gamja.tiggle.program.application.port.in.CreateProgramCommand;
import com.gamja.tiggle.program.application.port.in.CreateProgramUseCase;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/program")
public class CreateProgramController {
    private final CreateProgramUseCase createUseCase;

    @PostMapping("/create")
    @Operation(summary = "공연 정보 등록")
    public BaseResponse<String> create(@RequestPart CreateProgramRequest request, @RequestPart MultipartFile[] files) {
        try {
            // 필수 값 입력 체크
            validateRequest(request, files);

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

            createUseCase.createProgram(command);



        } catch (IllegalArgumentException | IllegalStateException e) {
            // 예외 처리: 필수 값이 비어 있는 경우나 유효성 검사 실패 시
            return new BaseResponse<>(BaseResponseStatus.BAD_REQUEST);
        } catch (Exception e) {
            // 기타 예외 처리
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    private void validateRequest(CreateProgramRequest request, MultipartFile[] files) {
        if (request.getProgramName() == null || request.getProgramName().isEmpty()
                || request.getProgramInfo() == null || request.getProgramInfo().isEmpty()
                || request.getReservationOpenDate() == null || request.getReservationCloseDate() == null
                || files == null || files.length == 0) {
            throw new IllegalArgumentException("필수 입력 값이 비어 있습니다.");
        }

        // 예매 날짜 체크
        if (request.getReservationOpenDate().isAfter(request.getReservationCloseDate())) {
            throw new IllegalArgumentException("예매 마감 날짜는 예매 오픈 날짜보다 이후여야 합니다.");
        }
    }
}
