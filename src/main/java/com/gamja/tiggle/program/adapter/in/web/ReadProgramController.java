package com.gamja.tiggle.program.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.program.adapter.in.web.response.ReadProgramResponse;
import com.gamja.tiggle.program.application.port.in.ReadProgramCommand;
import com.gamja.tiggle.program.application.port.in.ReadProgramUseCase;
import com.gamja.tiggle.program.domain.Program;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public List<ReadProgramResponse> readProgram(@RequestParam Long categoryId) throws BaseException {
        ReadProgramCommand command = ReadProgramCommand.builder()
                .categoryId(categoryId)
                .build();
        // 예외 처리 : ... 흑
        List<Program> program = readUseCase.readProgramAll(command);
        List<ReadProgramResponse> responses = program.stream()
                .map(p -> ReadProgramResponse.builder()
                        .programName(p.getProgramName())
                        .categoryId(p.getCategoryId())
                        .programInfo(p.getProgramInfo())
                        .programStartDate(p.getProgramStartDate())
                        .programEndDate(p.getProgramEndDate())
                        .imageFiles(p.getImageUrls())
                        .build())
                .collect(Collectors.toList());
        return responses;

    }
    // 실시간 Program 조회
    @GetMapping("/readRealTime")
    public ResponseEntity<List<ReadProgramResponse>> readRealTime(@RequestParam(required = false) LocalDateTime currentDateTime) throws BaseException{
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
                        .categoryId(p.getCategoryId())
                        .programInfo(p.getProgramInfo())
                        .programStartDate(p.getProgramStartDate())
                        .programEndDate(p.getProgramEndDate())
                        .imageFiles(p.getImageUrls())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);

//        } catch (BaseException e) {
//            return ResponseEntity.status(e.getStatus()).body(new BaseResponse(e.getStatus()));
//        }


    }


}
