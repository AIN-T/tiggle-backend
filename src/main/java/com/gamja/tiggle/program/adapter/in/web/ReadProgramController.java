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
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/program")
public class ReadProgramController {
    private final ReadProgramUseCase readUseCase;

    // Category 종류 별로 Program 조회
    @GetMapping("/readCategory")
    @Operation(summary = "카테고리 별 공연 정보 조회")
    public BaseResponse<List<ReadProgramResponse>> readProgram(@RequestParam Long categoryId,
                                                               @RequestParam Integer page,
                                                               @RequestParam Integer size) {
        try {
            ReadProgramCommand command = ReadProgramCommand.builder()
                    .categoryId(categoryId)
                    .page(page)
                    .size(size)
                    .build();

            List<Program> program = readUseCase.readProgramAll(command);
            List<ReadProgramResponse> responses = program.stream()
                    .map(p -> ReadProgramResponse.builder()
                            .programId(p.getId())
                            .programName(p.getProgramName())
                            .reservationOpenDate(p.getReservationOpenDate())
                            .programInfo(p.getProgramInfo())
                            .programStartDate(p.getProgramStartDate())
                            .programEndDate(p.getProgramEndDate())
                            .imageFiles(p.getImageUrls())
                            .locationName(p.getLocationName())
                            .build())
                    .collect(Collectors.toList());
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_PROGRAM_SUCCESS, responses);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_PROGRAM_FAIL, null);
        }
    }


    @GetMapping("/readRealTime")
    @Operation(summary = "티켓 오픈 시간이 임박한 공연을 페이지 별로 조회")
    public BaseResponse<List<ReadProgramResponse>> readPage(@RequestParam Integer page,
                                                            @RequestParam Integer size) {
        try{
            ReadProgramCommand command = ReadProgramCommand.builder()
                    .page(page)
                    .size(size)
                    .build();
            List<Program> program = readUseCase.readRealTimeAllPaged(command);
            List<ReadProgramResponse> responses = program.stream()
                    .map(p -> ReadProgramResponse.builder()
                            .programId(p.getId())
                            .programName(p.getProgramName())
                            .locationName(p.getLocationName())
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

    // keyword 별로 공연 검색
    @GetMapping("/search")
    @Operation(summary = "공연 검색")
    public BaseResponse<List<ReadProgramResponse>> search(@RequestParam String keyword,
                                                          @RequestParam Integer page,
                                                          @RequestParam Integer size) {
        try {
            validatePageRequest(page, size);

            ReadProgramCommand command = ReadProgramCommand.builder()
                    .keyword(keyword)
                    .page(page)
                    .size(size)
                    .build();
            List<Program> program = readUseCase.searchPrograms(command);
            List<ReadProgramResponse> responses = program.stream()
                    .map(p -> ReadProgramResponse.builder()
                            .programId(p.getId())
                            .programName(p.getProgramName())
                            .reservationOpenDate(p.getReservationOpenDate())
                            .programInfo(p.getProgramInfo())
                            .programStartDate(p.getProgramStartDate())
                            .programEndDate(p.getProgramEndDate())
                            .imageFiles(p.getImageUrls())
                            .locationName(p.getLocationName())
                            .build())
                    .collect(Collectors.toList());

            return new BaseResponse<>(BaseResponseStatus.SEARCH_PROGRAM_SUCCESS, responses);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.SEARCH_PROGRAM_FAIL, null);
        }
    }

    // 프로그램 상세 조회
    @GetMapping
    @Operation(summary = "공연 상세 정보 조회")
    public BaseResponse<GetProgramDetailResponse> GetProgramDetail(@RequestParam Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = (customUserDetails != null) ? customUserDetails.getUser() : null;

        try {
            Program program = readUseCase.GetProgramDetail(id, user);
            GetProgramDetailResponse getProgramDetailResponse = from(program);
            System.out.println(getProgramDetailResponse.getReservationOpenDate());
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
                .locationName(program.getLocationName())
                .programEndDate(program.getProgramEndDate())
                .programStartDate(program.getProgramStartDate())
                .reservationOpenDate(program.getReservationOpenDate())
                .programInfo(program.getProgramInfo())
                .runtime(program.getRuntime())
                .isLike(program.isLike())
                .build();
    }

    // 다른 페이지 값이 들어올 경우 예외처리
    private void validatePageRequest(Integer page, Integer size) throws BaseException {
        if (page == null || page < 0) {
            throw new BaseException(BaseResponseStatus.INVALID_PAGE);
        }
        if (size == null || size <= 0) {
            throw new BaseException(BaseResponseStatus.INVALID_SIZE);
        }
    }

}
