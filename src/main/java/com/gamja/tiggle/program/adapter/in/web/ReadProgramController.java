package com.gamja.tiggle.program.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.program.adapter.in.web.response.GetProgramDetailResponse;
import com.gamja.tiggle.program.adapter.in.web.response.ReadProgramResponse;
import com.gamja.tiggle.program.application.port.in.ReadProgramCommand;
import com.gamja.tiggle.program.application.port.in.ReadProgramUseCase;
import com.gamja.tiggle.program.domain.Program;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/program")
public class ReadProgramController {
    private final ReadProgramUseCase readUseCase;

    // Category 종류 별로 Program 조회
    @GetMapping("/readCategory")
    public BaseResponse<List<ReadProgramResponse>> readProgram(@RequestParam Long categoryId) {
        try {
            ReadProgramCommand command = ReadProgramCommand.builder()
                    .categoryId(categoryId)
                    .build();

            List<Program> program = readUseCase.readProgramAll(command);
            List<ReadProgramResponse> responses = program.stream()
                    .map(p -> ReadProgramResponse.builder()
                            .programName(p.getProgramName())
                            .programInfo(p.getProgramInfo())
                            .programStartDate(p.getProgramStartDate())
                            .programEndDate(p.getProgramEndDate())
                            .imageFiles(p.getImageUrls())
                            .build())
                    .collect(Collectors.toList());

            return new BaseResponse<>(BaseResponseStatus.SUCCESS, responses);

        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL, null);
        }
    }

    // 실시간 Program 조회
    @GetMapping("/readRealTime")
    public BaseResponse<List<ReadProgramResponse>> readRealTime(@RequestParam(required = false) LocalDateTime currentDateTime) {
        try {
            // 현재 시간이 없을 경우 기본적으로 현재 시간을 사용
            if (currentDateTime == null) {
                currentDateTime = LocalDateTime.now();
            }

            ReadProgramCommand command = ReadProgramCommand.builder()
                    .currentDateTime(currentDateTime)
                    .build();

            List<Program> program = readUseCase.readRealTimeAll(command);
            List<ReadProgramResponse> responses = program.stream()
                    .map(p -> ReadProgramResponse.builder()
                            .programName(p.getProgramName())
                            .reservationOpenDate(p.getReservationOpenDate())
                            .programInfo(p.getProgramInfo())
                            .programStartDate(p.getProgramStartDate())
                            .programEndDate(p.getProgramEndDate())
                            .imageFiles(p.getImageUrls())
                            .build())
                    .collect(Collectors.toList());
            return new BaseResponse<>(BaseResponseStatus.SUCCESS, responses);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL, null);
        }
    }

    @GetMapping("/read2")
    public BaseResponse<List<ReadProgramResponse>> readPage(@RequestParam Integer page,
                                                            @RequestParam Integer size,
                                                            @RequestParam(required = false) LocalDateTime currentDateTime) {

        try{
            if (currentDateTime == null) {
                currentDateTime = LocalDateTime.now();
            }

            ReadProgramCommand command = ReadProgramCommand.builder()
                    .currentDateTime(currentDateTime)
                    .page(page)
                    .size(size)
                    .build();
            List<Program> program = readUseCase.readRealTimeAllPaged(command);
            List<ReadProgramResponse> responses = program.stream()
                    .map(p -> ReadProgramResponse.builder()
                            .programName(p.getProgramName())
                            .reservationOpenDate(p.getReservationOpenDate())
                            .programInfo(p.getProgramInfo())
                            .programStartDate(p.getProgramStartDate())
                            .programEndDate(p.getProgramEndDate())
                            .imageFiles(p.getImageUrls())
                            .build())
                    .collect(Collectors.toList());
            return new BaseResponse<>(BaseResponseStatus.SUCCESS, responses);

        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL, null);
        }

    }



    // 프로그램 상세 조회
    @GetMapping
    public BaseResponse<GetProgramDetailResponse> GetProgramDetail(@RequestParam Long id) {

        try {
            Program program = readUseCase.GetProgramDetail(id);
            GetProgramDetailResponse getProgramDetailResponse = from(program);
            return new BaseResponse<>(getProgramDetailResponse);

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    // 비즈니스 로직

    private static GetProgramDetailResponse from(Program program) {
        // TODO 가격 정보
        return GetProgramDetailResponse.builder()
                .runtime(program.getRuntime())
                .age(program.getAge())
                .programName(program.getProgramName())
                .imageUrls(program.getImageUrls())
                .locationId(program.getLocationId())
                .programEndDate(program.getProgramEndDate())
                .programStartDate(program.getProgramStartDate())
                .programInfo(program.getProgramInfo())
                .runtime(program.getRuntime())
                .build();
    }

}
