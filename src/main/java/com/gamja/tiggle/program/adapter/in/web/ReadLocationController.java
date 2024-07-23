package com.gamja.tiggle.program.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.program.adapter.in.web.response.ReadLocationResponse;
import com.gamja.tiggle.program.application.port.in.ReadLocationCommand;
import com.gamja.tiggle.program.application.port.in.ReadLocationUseCase;
import com.gamja.tiggle.program.domain.Location;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/location")
public class ReadLocationController {
    private final ReadLocationUseCase readUseCase;

    @GetMapping("/readProgram")
    @Operation(summary = "장소별 공연 정보 조회", description = "해당 공연장에서 주최하는 공연 정보들을 조회하는 API 입니다.")
    public BaseResponse<List<ReadLocationResponse>> readProgram(@RequestParam Long locationId) {
        List<ReadLocationResponse> responses = null;
        try {
            ReadLocationCommand command = ReadLocationCommand.builder()
                    .locationId(locationId)
                    .build();
            List<Location> location = readUseCase.readProgramAll(command);
            responses = location.stream()
                    .map(l -> ReadLocationResponse.builder()
                            .id(l.getId())
                            .locationName(l.getLocationName())
                            .addressName(l.getAddressName())
                            .programNames(l.getProgramNames())
                            .build())
                    .collect(Collectors.toList());

        }
        catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(responses);
    }
}





